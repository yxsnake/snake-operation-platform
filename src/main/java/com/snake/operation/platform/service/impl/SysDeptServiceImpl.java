package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.snake.operation.platform.mapper.SysDeptMapper;
import com.snake.operation.platform.model.dto.SysDeptDTO;
import com.snake.operation.platform.model.entity.SysDept;
import com.snake.operation.platform.model.form.SysDeptForm;
import com.snake.operation.platform.service.SysDeptService;
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
        // 校验是否已存在
        // 查询 【部门名称】是否已存在
        long count = this.lambdaQuery().eq(SysDept::getDeptName, form.getName()).list().stream().count();
        BizAssert.isTrue("部门名称已存在",count > 0);
        SysDept sysDept = form.convert(SysDept.class);
        sysDept.setDeptName(form.getName());
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
        }else{
            // 校验是否已存在顶级部门
            long size = this.lambdaQuery().eq(SysDept::getParentId, SysDept.ROOT).list().stream().count();
            BizAssert.isTrue("已存在顶级部门,不允许创建多个顶级部门",size > 0);
        }
        sysDept.setParentId(parentId);
        DisabledEnum disabledEnum = DisabledEnum.getInstance(form.getStatus());
        BizAssert.isTrue("状态不能为空",Objects.isNull(disabledEnum));
        sysDept.setStatus(disabledEnum.getValue());
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
        SysDept sysDept = this.getBaseMapper().selectById(form.getId());
        BizAssert.isTrue("部门信息不存在",Objects.isNull(sysDept));

        String parentId = form.getParentId();
        BizAssert.isTrue("上级部门ID不能为空",StrUtil.isBlank(parentId));

        if(!StrUtil.equals(SysDept.ROOT,parentId)){
            SysDept parent = this.getBaseMapper().selectById(parentId);
            BizAssert.isTrue("上级部门信息不存在",Objects.isNull(parent));
        }
        sysDept.setDeptName(form.getName());
        sysDept.setRemark(form.getRemark());
        sysDept.setRank(form.getRank());
        sysDept.setParentId(form.getParentId());
        sysDept.setEmail(form.getEmail());
        sysDept.setPhone(form.getPhone());
        sysDept.setPersonInCharge(form.getPersonInCharge());
        sysDept.setStatus(form.getStatus());
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
    public List<SysDeptDTO> listAll() {
        List<SysDept> list = this.lambdaQuery()
                .orderByDesc(SysDept::getCreateTime)
                .list();
        if(CollUtil.isEmpty(list)){
            return Lists.newArrayList();
        }
        return list.stream()
                .map(item->item.convert(SysDeptDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByDeptId(String deptId) {
        // 校验当前部门是否有子级部门
        long count = this.lambdaQuery().eq(SysDept::getParentId, deptId).list().stream().count();
        BizAssert.isTrue("当前部门存在子级部门，不允许删除",count > 0);
        this.getBaseMapper().deleteById(deptId);
    }
}
