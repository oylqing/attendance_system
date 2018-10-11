package com.system.realm;

import com.system.pojo.Role;
import com.system.pojo.Staff;
import com.system.service.RoleService;
import com.system.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacey on 2017/6/30.
 */

@Component
public class LoginRealm extends AuthorizingRealm{

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     *     当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String userId = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            Staff staff = loginService.findById(userId);
            //获取角色对象
            role = roleService.findById(staff.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRoleName());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     *         login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String userId = (String) token.getPrincipal();
        //密码
        String password = new String((char[])token.getCredentials());

        Staff staff = null;
        try {
            staff = loginService.findById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (staff == null) {
            //没有该用户名
            throw new UnknownAccountException();
        } else if (!password.equals(staff.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }

        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(userId,password,getName());

        return aInfo;
    }
}
