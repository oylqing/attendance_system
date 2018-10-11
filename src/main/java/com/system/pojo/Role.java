package com.system.pojo;

public class Role {
    private Integer roleId;

    private String roleName;

    private String permissions;

    public Role(Integer roleId, String roleName, String permissions) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public Role() {
        super();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }
}