package com.system.pojo;

import java.util.Date;

public class Staff {
    private String staffId;

    private String staffName;

    private String sex;

    private Integer departmentId;

    private Integer role;

    private Date grade;

    private Date birthday;

    private String tel;

    private String email;

    private String hobby;

    private String password;

    public Staff(String staffId, String staffName, String sex, Integer departmentId, Integer role, Date grade, Date birthday, String tel, String email, String hobby, String password) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.sex = sex;
        this.departmentId = departmentId;
        this.role = role;
        this.grade = grade;
        this.birthday = birthday;
        this.tel = tel;
        this.email = email;
        this.hobby = hobby;
        this.password = password;
    }

    public Staff() {
        super();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}