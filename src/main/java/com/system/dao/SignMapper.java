package com.system.dao;

import com.system.pojo.Sign;

import java.util.List;
import java.util.Map;

public interface SignMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    int countById(String id);

    //员工分页查询签到记录
    List<Sign> findByPaging(Map<String,Object> paramMap) throws Exception;

    //判断当天是否已经签到
    int isSignIn(Sign signOut) throws Exception;

    //员工签退
    void signOut(Sign sign) throws Exception;

    //部门经理分页查询签到记录
    List<Sign> showSignRecord(Map<String,Object> paramMap) throws Exception;

    //查询记录总数
    int departmentSignCount(Map<String,Object> paramMap) throws Exception;
}