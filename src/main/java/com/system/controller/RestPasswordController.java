package com.system.controller;

import com.system.exception.CustomException;
import com.system.pojo.Staff;
import com.system.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jacey on 2017/7/6.
 */
@Controller
public class RestPasswordController {

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    // 本账户密码重置
    @RequestMapping(value = "/passwordReset", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = loginService.findById(id);

        if (!oldPassword.equals(staff.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            staff.setPassword(password1);
            loginService.updateById(id, staff);
        }

        return "redirect:/logout";
    }

}
