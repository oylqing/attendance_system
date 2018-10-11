package com.system.service.impl;

import com.system.dao.RoleMapper;
import com.system.pojo.Role;
import com.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findById(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }
}
