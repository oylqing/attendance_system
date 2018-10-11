package com.system.dao;

import com.system.pojo.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StaffMapper {
    int countByExample(StaffExample example);

    int deleteByExample(StaffExample example);

    int deleteByPrimaryKey(String staffId);

    int insert(Staff record);

    int insertSelective(Staff record);

    List<Staff> selectByExample(StaffExample example);

    Staff selectByPrimaryKey(String staffId);

    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    //更新个人信息
    void updateMassage(Map<String,Object> paramMap);

    //查找部门所有员工id
    List<String> findAllStaff(int department);

}