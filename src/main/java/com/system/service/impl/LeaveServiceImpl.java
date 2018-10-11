package com.system.service.impl;

/*import com.system.mapper.CollegeMapper;*/

import com.system.dao.LeaveMapper;
import com.system.pojo.Leave;
import com.system.pojo.Sign;
import com.system.service.LeaveService;
import com.system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*import com.system.mapper.StudentMapperCustom;*/


/**
 * Staff
 */

@Service
public class LeaveServiceImpl implements LeaveService {

    //使用spring 自动注入

    @Autowired
    private LeaveMapper leaveMapper;

    public void handinLeave(Map<String, Object> paramMap) throws ParseException {
        String leaveTime = (String) paramMap.get("leavetime");
        String leaveStartTime = leaveTime.substring(0, 19);
        String leaveEndTime = leaveTime.substring(22);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.US);
        Date StartTime =dateFormat.parse(leaveStartTime);
        Date EndTime =dateFormat.parse(leaveEndTime);

        Leave leave = new Leave();
        leave.setStaffId((String) paramMap.get("id"));
        leave.setDepartmentId((Integer) paramMap.get("departmentId"));
        leave.setReason((String) paramMap.get("reason"));
        leave.setLeaveStartTime(StartTime);
        leave.setLeaveEndTime(EndTime);

        leaveMapper.handinLeave(leave);
    }


    public int getCountLeave(String id) {
        return leaveMapper.countById(id);
    }

    public List<Leave> findByPaging(Map<String, Object> paramMap) throws Exception {
        List<Leave> list = leaveMapper.findByPaging(paramMap);
        return list;
    }

    @Override
    public Leave findByRecordId(int recordId) {
        return leaveMapper.findByRecordId(recordId);
    }

    @Override
    public int getUnhandledCount(int departmentId) {
        return leaveMapper.getUnhandledCount(departmentId);
    }

    @Override
    public int getDepartmentLeaveCount(int departmentId) {
        return leaveMapper.countByDepartmentId(departmentId);
    }

    public List<Leave> findByPagingD(Map<String, Object> paramMap) throws Exception {
        List<Leave> list = leaveMapper.findByPagingD(paramMap);
        return list;
    }

    @Override
    public void handleLeave(Map<String, Object> paramMap) {
        leaveMapper.handleLeave(paramMap);
    }

}
