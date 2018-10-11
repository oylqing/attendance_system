package com.system.pojo;

import java.util.Date;

public class Leave {
    private Integer recordId;

    private Integer departmentId;

    private String staffId;

    private Date haninTime;

    private Date leaveStartTime;

    private Date leaveEndTime;

    private String applicationState;

    private String reason;

    private String reply;

    private Date handleTime;

    public Leave(Integer recordId, Integer departmentId, String staffId, Date haninTime, Date leaveStartTime, Date leaveEndTime, String applicationState, String reason, String reply, Date handleTime) {
        this.recordId = recordId;
        this.departmentId = departmentId;
        this.staffId = staffId;
        this.haninTime = haninTime;
        this.leaveStartTime = leaveStartTime;
        this.leaveEndTime = leaveEndTime;
        this.applicationState = applicationState;
        this.reason = reason;
        this.reply = reply;
        this.handleTime = handleTime;
    }

    public Leave() {
        super();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public Date getHaninTime() {
        return haninTime;
    }

    public void setHaninTime(Date haninTime) {
        this.haninTime = haninTime;
    }

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public String getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(String applicationState) {
        this.applicationState = applicationState == null ? null : applicationState.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}