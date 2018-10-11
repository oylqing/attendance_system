package com.system.pojo;

import java.util.Date;

public class StaffCustom extends Staff{
    //部门名称
    private String departmentName;

    //职位名称
    private String roleName;

    public void setdepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getdepartmentName() {
        return departmentName;
    }

    public String getroleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }



}