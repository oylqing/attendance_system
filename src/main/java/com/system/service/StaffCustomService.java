package com.system.service;

import com.system.pojo.Staff;
import com.system.pojo.StaffCustom;

import java.util.List;
import java.util.Map;

/**
 * StaffCustom员工拓展类Service层
 */
public interface StaffCustomService {

    //通过员工编号查找员工信息
    StaffCustom findById(String id) throws Exception;

    //获取公司员工总数
    int getStaffCount()throws Exception;

    //分页获取员工信息
    List<StaffCustom> findByPaging(int toPageNo)throws Exception;

    //更新员工部门和职位
    void editStaff(Map<String,Object> paramMap)throws Exception;

    //根据员工姓名搜索员工信息
    List<StaffCustom> findByName(String findByName)throws Exception;

    //根据员工编号移除员工信息
    void removeById(String id);

    //保存员工信息
    Boolean save(StaffCustom staffCustom) throws Exception;
}
