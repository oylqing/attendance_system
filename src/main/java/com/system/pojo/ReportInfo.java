package com.system.pojo;

import java.util.Date;

public class ReportInfo {
    private Integer reportId;

    private Date createDate;

    private Integer departmentId;

    private String departmentName;

    private Integer dayLateCount;

    private Integer dayEarlyCount;

    private Integer monthLateCount;

    private Integer monthEarlyCount;

    private Integer yearLateCount;

    private Integer yearEarlyCount;

    public ReportInfo(Integer reportId, Date createDate, Integer departmentId, String departmentName, Integer dayLateCount, Integer dayEarlyCount, Integer monthLateCount, Integer monthEarlyCount, Integer yearLateCount, Integer yearEarlyCount) {
        this.reportId = reportId;
        this.createDate = createDate;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.dayLateCount = dayLateCount;
        this.dayEarlyCount = dayEarlyCount;
        this.monthLateCount = monthLateCount;
        this.monthEarlyCount = monthEarlyCount;
        this.yearLateCount = yearLateCount;
        this.yearEarlyCount = yearEarlyCount;
    }

    public ReportInfo() {
        super();
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getDayLateCount() {
        return dayLateCount;
    }

    public void setDayLateCount(Integer dayLateCount) {
        this.dayLateCount = dayLateCount;
    }

    public Integer getDayEarlyCount() {
        return dayEarlyCount;
    }

    public void setDayEarlyCount(Integer dayEarlyCount) {
        this.dayEarlyCount = dayEarlyCount;
    }

    public Integer getMonthLateCount() {
        return monthLateCount;
    }

    public void setMonthLateCount(Integer monthLateCount) {
        this.monthLateCount = monthLateCount;
    }

    public Integer getMonthEarlyCount() {
        return monthEarlyCount;
    }

    public void setMonthEarlyCount(Integer monthEarlyCount) {
        this.monthEarlyCount = monthEarlyCount;
    }

    public Integer getYearLateCount() {
        return yearLateCount;
    }

    public void setYearLateCount(Integer yearLateCount) {
        this.yearLateCount = yearLateCount;
    }

    public Integer getYearEarlyCount() {
        return yearEarlyCount;
    }

    public void setYearEarlyCount(Integer yearEarlyCount) {
        this.yearEarlyCount = yearEarlyCount;
    }
}