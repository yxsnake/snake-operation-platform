package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.ModuleDTO;
import com.snake.operation.platform.model.entity.ModuleEntity;
import com.snake.operation.platform.model.form.ModuleForm;
import com.snake.operation.platform.model.queries.ModulePageEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public interface ModuleEntityService extends IService<ModuleEntity> {
    void create(ModuleForm form);

    void modify(ModuleForm form);

    IPage<ModuleDTO> pageList(QueryFilter<ModulePageEqualsQueries, BaseFuzzyQueries> queryFilter);

    ModuleDTO detail(String moduleId);

    void removeByModuleId(String moduleId);
}
