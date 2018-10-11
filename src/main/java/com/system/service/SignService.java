package com.system.service;

import com.system.pojo.Sign;

import java.util.List;
import java.util.Map;

public interface SignService {
    int getCountSign(String id) throws Exception;

    //获取分页考勤信息
    List<Sign> findByPaging(Map<String, Object> paramMap) throws Exception;

    //签到
    void signIn(Sign sign) throws Exception;

    //判断当天是否已签到
    int isSignIn(Sign sign) throws Exception;

    //签退
    void signOut(Sign sign) throws Exception;


    List<Sign> showSignRecord(Map<String,Object> paramMap) throws Exception;

    //查询部门某天签到记录
    int getDepartmentSignCount(Map<String,Object> paramMap) throws Exception;
}
