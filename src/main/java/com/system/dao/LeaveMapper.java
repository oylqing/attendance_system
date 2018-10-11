package com.system.dao;

import com.system.pojo.Leave;

import java.util.List;
import java.util.Map;

public interface LeaveMapper {

    int deleteByPrimaryKey(Integer recordId);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    //提交请假申请
    void handinLeave(Leave leave);

    int countById(String id);

    //员工分页查询请假记录
    List<Leave> findByPaging(Map<String,Object> paramMap);

    //员工查询请假记录详情
    Leave findByRecordId(int recordId);

    //部门经理菜单页获取待处理请假单数量
    int getUnhandledCount(int departmentId);

    int countByDepartmentId(int departmentId);

    List<Leave> findByPagingD(Map<String,Object> paramMap);

    void handleLeave(Map<String,Object> paramMap);

    List<Leave> loadLeaveList();
}