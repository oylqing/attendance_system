package com.system.service;

import com.system.pojo.*;

import java.util.List;

public interface AdminService {

    List<ReportInfo> loadReportInfoData(ReportInfo reportInfo);

    List<ReportDetail> loadReportDetailData(String reportId);

    List<Leave> loadLeaveList() throws Exception;
}
