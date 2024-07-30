package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.SysDeptDTO;
import com.snake.operation.platform.model.dto.SysDeptDetailDTO;
import com.snake.operation.platform.model.entity.SysDept;
import com.snake.operation.platform.model.form.SysDeptForm;
import com.snake.operation.platform.model.fuzzy.SysDeptFuzzyQueries;
import com.snake.operation.platform.model.queries.SysDeptEqualsQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    void create(SysDeptForm form);

    void modify(SysDeptForm form);

    SysDeptDTO detail(String deptId);

    List<SysDeptDetailDTO> queryListCondition(QueryFilter<SysDeptEqualsQueries, SysDeptFuzzyQueries> queryFilter);
}
