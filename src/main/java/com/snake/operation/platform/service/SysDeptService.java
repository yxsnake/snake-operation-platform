package com.snake.operation.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snake.operation.platform.model.dto.SysDeptDTO;
import com.snake.operation.platform.model.entity.SysDept;
import com.snake.operation.platform.model.form.SysDeptForm;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {
    void create(SysDeptForm form);

    void modify(SysDeptForm form);

    SysDeptDTO detail(String deptId);

    List<SysDeptDTO> listAll();

    void removeByDeptId(String deptId);
}
