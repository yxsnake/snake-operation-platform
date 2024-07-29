package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.SysRoleMapper;
import com.snake.operation.platform.mapper.SysUserMapper;
import com.snake.operation.platform.model.dto.SysRoleDTO;
import com.snake.operation.platform.model.dto.SysUserDTO;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.entity.SysUser;
import com.snake.operation.platform.model.entity.SysUserRole;
import com.snake.operation.platform.model.enums.SysUserStatusEnum;
import com.snake.operation.platform.model.form.SysUserEnableOrDisableForm;
import com.snake.operation.platform.model.form.SysUserForm;
import com.snake.operation.platform.model.fuzzy.SysUserFuzzyQueries;
import com.snake.operation.platform.model.queries.SysUserPageEqualsQueries;
import com.snake.operation.platform.service.SysUserRoleService;
import com.snake.operation.platform.service.SysUserService;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleMapper sysRoleMapper;

    private final SysUserRoleService sysUserRoleService;
    @Override
    public IPage<SysUserDTO> pageList(QueryFilter<SysUserPageEqualsQueries, SysUserFuzzyQueries> queryFilter) {
        BizAssert.isTrue("请求参数不能为空", Objects.isNull(queryFilter));
        SysUserPageEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        SysUserFuzzyQueries fuzzyQueries = queryFilter.getFuzzyQueries();
        BizAssert.isTrue("equalsQueries参数不能为空", Objects.isNull(equalsQueries));
        BizAssert.isTrue("fuzzyQueries参数不能为空", Objects.isNull(fuzzyQueries));

        IPage<SysUserDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(Objects.nonNull(equalsQueries.getStatus()), SysUser::getStatus, equalsQueries.getStatus())
                        .like(StrUtil.isNotBlank(fuzzyQueries.getUsername()), SysUser::getUsername, fuzzyQueries.getUsername())
        ).convert(item -> item.convert(SysUserDTO.class));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserDTO create(SysUserForm sysUserForm) {
        SysUser sysUser = sysUserForm.convert(SysUser.class);
        String userId = IdWorker.getIdStr();
        sysUser.setUserId(userId);
        sysUser.setStatus(SysUserStatusEnum.NORMAL.getValue());
        sysUser.setPassword(sysUserForm.getUsername());
        if(StrUtil.isBlank(sysUser.getAvatar())){
            sysUser.setAvatar(SysUser.DEFAULT_AVATAR);
        }
        this.save(sysUser);
        List<String> roleIds = sysUserForm.getRoleIds();
        if(CollUtil.isNotEmpty(roleIds)){
            // 查询角色信息
            List<SysRole> sysRoles = sysRoleMapper.selectList(
                    Wrappers.lambdaQuery(SysRole.class).in(SysRole::getRoleId, roleIds));
            if(CollUtil.isNotEmpty(sysRoles)){
                List<SysUserRole> sysUserRoles = sysUserRoleService.buildUserRoleList(userId,sysRoles);
                sysUserRoleService.saveBatch(sysUserRoles);
            }
        }
        return sysUser.convert(SysUserDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserDTO modify(SysUserForm sysUserForm) {
        SysUser sysUser = this.getBaseMapper().selectById(sysUserForm.getUserId());
        BizAssert.isTrue("用户不存在",Objects.isNull(sysUser));
        sysUser.setName(sysUserForm.getName());
        if(StrUtil.isNotBlank(sysUserForm.getAvatar())){
            sysUser.setAvatar(sysUserForm.getAvatar());
        }
        if(Objects.nonNull(sysUserForm.getGender())){
            sysUser.setGender(sysUserForm.getGender());
        }
        this.getBaseMapper().updateById(sysUser);

        String userId = sysUserForm.getUserId();
        sysUserRoleService.removeRoleByUserId(userId);
        List<String> roleIds = sysUserForm.getRoleIds();
        if(CollUtil.isNotEmpty(roleIds)){
            // 查询角色信息
            List<SysRole> sysRoles = sysRoleMapper.selectList(
                    Wrappers.lambdaQuery(SysRole.class).in(SysRole::getRoleId, roleIds));
            if(CollUtil.isNotEmpty(sysRoles)){
                List<SysUserRole> sysUserRoles = sysUserRoleService.buildUserRoleList(userId,sysRoles);
                sysUserRoleService.saveBatch(sysUserRoles);
            }
        }
        return sysUser.convert(SysUserDTO.class);
    }

    @Override
    public SysUserDTO detail(String userId) {
        SysUser sysUser = this.getBaseMapper().selectById(userId);
        BizAssert.isTrue("用户不存在",Objects.isNull(sysUser));
        SysUserDTO sysUserDTO = sysUser.convert(SysUserDTO.class);
        // 查询角色信息
        List<SysRole> sysRoles = sysUserRoleService.getRoleListByUserId(userId);
        if(CollUtil.isNotEmpty(sysRoles)){
            List<SysRoleDTO> roles = sysRoles.stream().map(item -> item.convert(SysRoleDTO.class))
                    .collect(Collectors.toList());
            sysUserDTO.setRoles(roles);
        }
        return sysUserDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserDTO enableOrDisable(SysUserEnableOrDisableForm form) {
        SysUser sysUser = this.getBaseMapper().selectById(form.getUserId());
        BizAssert.isTrue("用户不存在",Objects.isNull(sysUser));
        SysUserStatusEnum sysUserStatusEnum = SysUserStatusEnum.getInstance(form.getStatus());
        BizAssert.isTrue("账号状态参数错误",Objects.isNull(sysUserStatusEnum));
        sysUser.setStatus(form.getStatus());
        this.getBaseMapper().updateById(sysUser);
        return sysUser.convert(SysUserDTO.class);
    }
}
