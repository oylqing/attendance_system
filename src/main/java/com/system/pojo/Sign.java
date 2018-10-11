package com.system.pojo;

import java.util.Date;

public class Sign {
    private Integer recordId;

    private Integer departmentId;

    private String staffId;

    private Date signInTime;

    private String signInState;

    private Date signOutTime;

    private String signOutState;

    private Date updateTime;

    public Sign(Integer recordId, Integer departmentId, String staffId, Date signInTime, String signInState, Date signOutTime, String signOutState, Date updateTime) {
        this.recordId = recordId;
        this.departmentId = departmentId;
        this.staffId = staffId;
        this.signInTime = signInTime;
        this.signInState = signInState;
        this.signOutTime = signOutTime;
        this.signOutState = signOutState;
        this.updateTime = updateTime;
    }

    public Sign() {
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

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInState() {
        return signInState;
    }

    public void setSignInState(String signInState) {
        this.signInState = signInState == null ? null : signInState.trim();
    }

    public Date getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Date signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getSignOutState() {
        return signOutState;
    }

    public void setSignOutState(String signOutState) {
        this.signOutState = signOutState == null ? null : signOutState.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}