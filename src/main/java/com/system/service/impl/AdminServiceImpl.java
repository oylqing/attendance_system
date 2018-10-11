package com.system.service.impl;

import com.system.dao.LeaveMapper;
import com.system.dao.ReportDetailMapper;
import com.system.dao.ReportInfoMapper;
import com.system.pojo.*;
import com.system.service.AdminService;
import com.system.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ReportInfoMapper reportInfoMapper;

    @Autowired
    private ReportDetailMapper reportDetailMapper;

    @Autowired
    private LeaveMapper leaveMapper;

    private static PropertiesUtil scheduledFile = new PropertiesUtil("scheduled.properties");

    private static PropertiesUtil reportFile = new PropertiesUtil("report.properties");

    public List<ReportInfo> loadReportInfoData (ReportInfo reportInfo) {
        // 查询条件拼接
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(reportInfo != null) {
            paramMap.put("departmentId", reportInfo.getDepartmentId());
            paramMap.put("createDate", reportInfo.getCreateDate());
            /* recostExpense(paramMap);*/
        }
        List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
        if (reportInfo.getDepartmentId().equals(0)){
            reportInfoList = reportInfoMapper.selectReportInfoDataAll(paramMap);
        } else {
            reportInfoList = reportInfoMapper.selectReportInfoData(paramMap);
        }
        return reportInfoList;

    }

    public List<ReportDetail> loadReportDetailData(String reportId){
        // 数据查询
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("reportId", reportId);
        List<ReportDetail> reportInfoList=new ArrayList<>();
        reportInfoList = reportDetailMapper.selectReportDetailData(paramMap);
        return reportInfoList;
    }

    @Override
    public List<Leave> loadLeaveList() throws Exception {
        return leaveMapper.loadLeaveList();
    }


    @Scheduled(cron="0 59 20 * * ?")
    public void recostExpense() {
        // 统计日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String reportDate = sdf.format(cal.getTime());
        System.out.println("考勤统计定时任务开始执行，统计日期：" + reportDate);
        // 判断是否为定时任务节点
        String scheduledNode = scheduledFile.readProperty("scheduledNode");
        if (scheduledNode != null && "Y".equals(scheduledNode)) {
            // 统计出单情况
            Properties Pro = reportFile.getProperties();
            Enumeration<?> En = Pro.propertyNames();
            while (En.hasMoreElements()) {
                String departmentId = (String) En.nextElement();
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("reportDate", reportDate);
                paramMap.put("departmentId", departmentId);
                reportInfoMapper.executeReportProc(paramMap);
                System.out.println( departmentId + " is complete!");
            }
        } else {
            System.out.println("非定时任务节点，退出定时");
        }
    }

}
