package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.snake.operation.platform.mapper.SysDeptMapper;
import com.snake.operation.platform.model.dto.SysDeptDTO;
import com.snake.operation.platform.model.dto.SysDeptDetailDTO;
import com.snake.operation.platform.model.entity.SysDept;
import com.snake.operation.platform.model.form.SysDeptForm;
import com.snake.operation.platform.model.fuzzy.SysDeptFuzzyQueries;
import com.snake.operation.platform.model.queries.SysDeptEqualsQueries;
import com.snake.operation.platform.service.SysDeptService;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.enums.DisabledEnum;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysDeptForm form) {
        // 查询 【部门名称】是否已存在
        long count = this.lambdaQuery().eq(SysDept::getDeptName, form.getDeptName()).list().stream().count();
        BizAssert.isTrue("部门名称已存在",count > 0);
        SysDept sysDept = form.convert(SysDept.class);
        String deptId = IdWorker.getIdStr();
        sysDept.setDeptId(deptId);
        String parentId = form.getParentId();
        if(StrUtil.isBlank(parentId)){
            parentId = SysDept.ROOT;
        }
        if(!StrUtil.equals(parentId,SysDept.ROOT)){
            SysDept parent = this.lambdaQuery().eq(SysDept::getDeptId, parentId).list().stream().findFirst().orElse(null);
            BizAssert.isTrue("上级部门不存在", Objects.isNull(parent));
            BizAssert.isTrue("上级部门已禁用,不允许添加", DisabledEnum.DISABLE.getValue().equals(parent.getStatus()));
        }
        sysDept.setParentId(parentId);
        sysDept.setStatus(DisabledEnum.NORMAL.getValue());
        sysDept.setCreateTime(DateUtil.date());
        if(Objects.isNull(sysDept.getRank())){
            sysDept.setRank(DateUtil.date().getTime());
        }
        this.save(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(SysDeptForm form) {
        // 查询当前部门是否存在
        SysDept sysDept = this.getBaseMapper().selectById(form.getDeptId());
        BizAssert.isTrue("部门信息不存在",Objects.isNull(sysDept));

        String parentId = form.getParentId();
        BizAssert.isTrue("上级部门ID不能为空",StrUtil.isBlank(parentId));

        if(!StrUtil.equals(SysDept.ROOT,parentId)){
            SysDept parent = this.getBaseMapper().selectById(parentId);
            BizAssert.isTrue("上级部门信息不存在",Objects.isNull(parent));
        }
        BeanUtils.copyProperties(form,sysDept);
        sysDept.setUpdateTime(DateUtil.date());

        this.getBaseMapper().updateById(sysDept);
    }

    @Override
    public SysDeptDTO detail(String deptId) {
        SysDept sysDept = this.getBaseMapper().selectById(deptId);
        if(Objects.isNull(sysDept)){
            return null;
        }
        return sysDept.convert(SysDeptDTO.class);
    }

    @Override
    public List<SysDeptDetailDTO> queryListCondition(QueryFilter<SysDeptEqualsQueries, SysDeptFuzzyQueries> queryFilter) {
        SysDeptEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        if(Objects.isNull(equalsQueries)){
            equalsQueries = new SysDeptEqualsQueries();
        }
        SysDeptFuzzyQueries fuzzyQueries = queryFilter.getFuzzyQueries();
        if(Objects.isNull(fuzzyQueries)){
            fuzzyQueries = new SysDeptFuzzyQueries();
        }
        List<SysDept> list = this.lambdaQuery().eq(Objects.nonNull(equalsQueries.getStatus()), SysDept::getStatus, equalsQueries.getStatus())
                .like(StrUtil.isNotBlank(fuzzyQueries.getDeptName()), SysDept::getDeptName, fuzzyQueries.getDeptName())
                .list();
        if(CollUtil.isEmpty(list)){
            return Lists.newArrayList();
        }
        return list.stream()
                .map(item->item.convert(SysDeptDetailDTO.class))
                .collect(Collectors.toList());
    }
}
