package com.system.controller;

import com.system.exception.CustomException;
import com.system.pojo.*;
import com.system.service.DepartmentService;
import com.system.service.LeaveService;
import com.system.service.StaffService;
import com.system.service.SignService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/staff")
public class StaffController {

    @Resource(name = "staffServiceImpl")
    private StaffService staffService;

    @Resource(name = "signServiceImpl")
    private SignService signService;

    @Resource(name = "leaveServiceImpl")
    private LeaveService leaveService;

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    //签到签退
    @RequestMapping(value="/sign")
    public String staffSign() throws Exception {
        return "/staff/sign";
    }

    //签到
    @RequestMapping(value="/signIn")
    public void staffSignIn(HttpServletResponse response) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);
        //获取当前时间
        Date sign_in_time = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
        String time=dateFormat.format(sign_in_time);

        Sign signIn= new Sign();
        signIn.setStaffId(id);
        signIn.setDepartmentId(staff.getDepartmentId());
        signIn.setSignInTime(sign_in_time);

        //判断当天是否已经签到
        int isSignIn = signService.isSignIn(signIn);

        if (isSignIn !=0){
            response.getWriter().println("今日已签到！");
        }
        else {
            //判断签到时间状态，8:00之前0-正常，之后1-迟到
            Date beginTime = dateFormat.parse("09:00:00");
            Calendar date = Calendar.getInstance();
            date.setTime(dateFormat.parse(time));
            Calendar begin = Calendar.getInstance();
            begin.setTime(beginTime);
            if (date.after(begin)) {
                signIn.setSignInState("1");
            } else
                signIn.setSignInState("0");
            //签到记录插入表sign_record
            signService.signIn(signIn);
            //签到时间返回给前端页面
            response.getWriter().println(time);
        }
    }

    //签退
    @RequestMapping(value="/signOut")
    public void staffSignOut(HttpServletResponse response) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);
        //获取当前时间
        Date sign_out_time = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
        String time=dateFormat.format(sign_out_time);

        Sign signOut= new Sign();
        signOut.setStaffId(id);
        signOut.setDepartmentId(staff.getDepartmentId());
        signOut.setSignOutTime(sign_out_time);

        //判断当天是否已经签到
        int isSignIn = signService.isSignIn(signOut);
        if(isSignIn == 0){
            response.getWriter().println("今日还未签到，请先签到");
        }else {
            //判断签到时间状态，18:00之后0-正常，之前1-迟到
            Date endTime = dateFormat.parse("18:00:00");
            Calendar date = Calendar.getInstance();
            date.setTime(dateFormat.parse(time));
            Calendar end = Calendar.getInstance();
            end.setTime(endTime);
            if (date.before(end)) {
                signOut.setSignOutState("1");
            } else signOut.setSignOutState("0");
            //更新签退记录到已有签到记录
            signService.signOut(signOut);
            //返回时间到前端页面
            response.getWriter().println("签退成功\n"+"签退时间："+time);
        }
    }

    //显示个人信息
    @RequestMapping(value="/showMassage", method = {RequestMethod.GET})
    public String staffMassageShow(Model model) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        //通过id获取员工信息
        Staff staff = staffService.findById(id);
        if (staff == null) {
            throw new CustomException("未找到员工信息");
        }
        List<Department> list = departmentService.finAll();

        model.addAttribute("departmentList", list);
        model.addAttribute("staff", staff);
        return "/staff/showMassage";
    }

    //更新个人信息
    @RequestMapping(value="/showMassage", method = {RequestMethod.POST})
    public String staffMassageUpdate(String tel,String email,String hobby) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id",id);
        paramMap.put("tel",tel);
        paramMap.put("email",email);
        paramMap.put("hobby",hobby);
        staffService.updateMassage(paramMap);

        return "redirect:/staff/showMassage";
    }

    //查询考勤记录
    @RequestMapping(value = "/signRecord")
    public String signRecordShow(Model model, Integer page) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        List<Sign> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(signService.getCountSign(id));
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //按页码和用户id查询考勤记录
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("id", id);
            list = signService.findByPaging(paramMap);
        } else {
            pagingVO.setToPageNo(page);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("id", id);
            list = signService.findByPaging(paramMap);
        }

        model.addAttribute("signList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/staff/signRecord";
    }

    //请假申请
    @RequestMapping(value="/leave")
    public String leave() throws Exception {
        return "/staff/leave";
    }
    @RequestMapping(value="/handinLeave")
    public String handinLeave(String leavetime,String reason) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);

        //将请假申请写入数据库
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("leavetime", leavetime);
        paramMap.put("reason", reason);
        paramMap.put("id", id);
        paramMap.put("departmentId",staff.getDepartmentId());
        /*System.out.println(reason);*/
        leaveService.handinLeave(paramMap);

        return "redirect:/staff/leaveRecord";
    }


    //查询请假记录及状态
    @RequestMapping(value = "/leaveRecord")
    public String leaveRecordShow(Model model, Integer page) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        List<Leave> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(leaveService.getCountLeave(id));
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //按页码和用户id查询请假记录
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("id", id);
            list = leaveService.findByPaging(paramMap);
        } else {
            pagingVO.setToPageNo(page);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("id", id);
            list = leaveService.findByPaging(paramMap);
        }

        model.addAttribute("leaveList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/staff/leaveRecord";
    }

    // 显示请假申请详细信息
    @RequestMapping(value = "/leaveDetail")
    public String gradeCourse(Integer recordId, Model model) throws Exception {
        if (recordId == null) {
            return "";
        }
        Leave leaveDetail= leaveService.findByRecordId(recordId);
        model.addAttribute("leaveDetail", leaveDetail);
        return "/staff/showLeaveDetail";
    }

    //修改密码
    @RequestMapping(value = "/passwordReset")
    public String passwordRest() throws Exception {
        return "/staff/passwordReset";
    }



}
