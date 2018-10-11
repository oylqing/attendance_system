package com.system.service;

import com.system.pojo.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService {

    Role findById(Integer id) throws Exception;

}
