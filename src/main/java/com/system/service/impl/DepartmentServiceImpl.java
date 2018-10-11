package com.system.service.impl;

import com.system.dao.DepartmentMapper;
import com.system.pojo.Department;
import com.system.pojo.DepartmentExample;
import com.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jacey on 2017/6/30.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> finAll() throws Exception {
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();

        criteria.andDepartmentIdIsNotNull();


        return departmentMapper.selectByExample(departmentExample);
    }
}
