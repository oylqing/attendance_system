package com.system.dao;

import com.system.pojo.PagingVO;
import com.system.pojo.StaffCustom;

import java.util.List;
import java.util.Map;

public interface StaffCustomMapper {

    //分页查询员工信息
    List<StaffCustom> findByPaging(PagingVO pagingVO) throws Exception;

    //获取员工总数
    int getStaffCount();

    //通过员工编号查找员工信息
    StaffCustom findById(String id) throws Exception;

    void editStaff(Map<String,Object> paramMap) throws Exception;

    List<StaffCustom> findByName(String findByName) throws Exception;

    void removeById(String id);

    StaffCustom selectByPrimaryKey(String staffId) throws Exception;

    void insert(StaffCustom staffCustom) throws Exception;
}
