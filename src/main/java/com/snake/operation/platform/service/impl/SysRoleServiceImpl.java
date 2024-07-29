package com.snake.operation.platform.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.SysMenuMapper;
import com.snake.operation.platform.mapper.SysRoleMapper;
import com.snake.operation.platform.model.dto.SysRoleDTO;
import com.snake.operation.platform.model.entity.SysMenu;
import com.snake.operation.platform.model.entity.SysRole;
import com.snake.operation.platform.model.form.RoleAuthForm;
import com.snake.operation.platform.model.form.SysRoleForm;
import com.snake.operation.platform.model.fuzzy.SysRoleFuzzyQueries;
import com.snake.operation.platform.model.queries.SysRolePageEqualsQueries;
import com.snake.operation.platform.service.SysRoleMenuService;
import com.snake.operation.platform.service.SysRoleService;
import com.snake.operation.platform.service.SysUserRoleService;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.enums.DeletedEnum;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleMenuService sysRoleMenuService;

    private final SysMenuMapper sysMenuMapper;

    @Override
    public IPage<SysRoleDTO> pageList(QueryFilter<SysRolePageEqualsQueries, SysRoleFuzzyQueries> queryFilter) {
        BizAssert.isTrue("请求参数不能为空", Objects.isNull(queryFilter));
        SysRolePageEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        SysRoleFuzzyQueries fuzzyQueries = queryFilter.getFuzzyQueries();
        BizAssert.isTrue("equalsQueries参数不能为空", Objects.isNull(equalsQueries));
        BizAssert.isTrue("fuzzyQueries参数不能为空", Objects.isNull(fuzzyQueries));
        IPage<SysRoleDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(SysRole.class)
                        .eq(Objects.nonNull(equalsQueries.getRoleCode()), SysRole::getRoleCode, equalsQueries.getRoleCode())
                        .like(StrUtil.isNotBlank(fuzzyQueries.getRoleName()), SysRole::getRoleName, fuzzyQueries.getRoleName())
        ).convert(item -> item.convert(SysRoleDTO.class));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRoleDTO create(SysRoleForm form) {
        // 校验角色编码是否已存在
        Long size = this.lambdaQuery().eq(SysRole::getRoleCode, form.getRoleCode()).count();
        BizAssert.isTrue("角色编码已存在",size>0);
        size = this.lambdaQuery().eq(SysRole::getRoleName, form.getRoleName()).count();
        BizAssert.isTrue("角色名称已存在",size > 0);
        SysRole sysRole = form.convert(SysRole.class);
        String roleId = IdWorker.getIdStr();
        sysRole.setRoleId(roleId);
        sysRole.setDeleted(DeletedEnum.NORMAL.getValue());
        this.save(sysRole);
        return sysRole.convert(SysRoleDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRoleDTO modify(SysRoleForm form) {
        SysRole sysRole = this.getBaseMapper().selectById(form.getRoleId());
        BizAssert.isTrue("角色信息不存在",Objects.isNull(sysRole));
        Long size = this.lambdaQuery().eq(SysRole::getRoleCode, form.getRoleCode())
                .ne(SysRole::getRoleId,sysRole.getRoleId())
                .count();
        BizAssert.isTrue("角色名称已存在",size > 0);
        sysRole.setRoleName(form.getRoleName());
        sysRole.setRemark(form.getRemark());
       this.getBaseMapper().update(sysRole,Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getRoleId,form.getRoleId()));
       return sysRole.convert(SysRoleDTO.class);
    }

    @Override
    public SysRoleDTO detail(String roleId) {
        SysRole sysRole = this.getBaseMapper().selectById(roleId);
        BizAssert.isTrue("角色信息不存在",Objects.isNull(sysRole));
        return sysRole.convert(SysRoleDTO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void roleAuth(RoleAuthForm form) {
        // 删除角色的授权关系
        sysRoleMenuService.removeRoleAuth(form.getRoleId());
        // 根据菜单 id 查询菜单信息
        List<SysMenu> sysMenus = sysMenuMapper.selectList(
                Wrappers.lambdaQuery(SysMenu.class)
                .in(SysMenu::getMenuId, form.getMenuIds())
        );
        // 给角色绑定菜单权限
        sysRoleMenuService.roleAuthMenus(form.getRoleId(),sysMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRoleDTO removeByRoleId(String roleId) {
        SysRole sysRole = this.getBaseMapper().selectById(roleId);
        BizAssert.isTrue("角色信息不存在",Objects.isNull(sysRole));
        // 校验角色是否已绑定用户，如果绑定用户，阻止删除
        Boolean isBind = sysUserRoleService.checkRoleBindUser(roleId);
        BizAssert.isTrue("当前角色已绑定用户，请先解绑改角色下的用户",isBind);
        sysUserRoleService.removeRoleByRoleId(roleId);
        sysRole.setDeleted(DeletedEnum.DEL.getValue());
        this.getBaseMapper().updateById(sysRole);
        return sysRole.convert(SysRoleDTO.class);
    }
}
