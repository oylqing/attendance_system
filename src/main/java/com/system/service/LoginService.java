package com.system.service;

import com.system.pojo.Staff;

/**
 *
 *
 */
public interface LoginService {

    //根据编号查找用户
    Staff findById(String id) throws Exception;

    //保存用户登录信息
    void save(Staff staff) throws Exception;

    //根据编号删除
    void removeById(String id) throws Exception;

    //根据编号更新
    void updateById(String id, Staff staff);

}
