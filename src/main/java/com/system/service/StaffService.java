package com.system.service;

import com.system.pojo.Staff;

import java.util.List;
import java.util.Map;

/**
 * Student学生Service层
 */
public interface StaffService {

    //通过员工编号查找员工信息
    Staff
    findById(String id) throws Exception;

    //更新员工信息
    void updateMassage(Map<String, Object> paramMap) throws Exception;

    //分页查询部门所有员工
    List<String> findAllStaff(int department) throws Exception;

}
