package com.system.controller;

import com.system.exception.CustomException;
import com.system.pojo.*;
import com.system.service.DepartmentService;
import com.system.service.LeaveService;
import com.system.service.SignService;
import com.system.service.StaffService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/managers")
public class ManagerController {
    @Resource(name = "staffServiceImpl")
    private StaffService staffService;

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    @Resource(name = "signServiceImpl")
    private SignService signService;

    @Resource(name = "leaveServiceImpl")
    private LeaveService leaveService;

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
        return "/managers/showMassage";
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

        return "redirect:/managers/showMassage";
    }

    //查询部门某天签到情况
    @RequestMapping(value = "/showSignRecord"/*, method = {RequestMethod.POST}*/)
    public String showSignRecord(String querytime, Integer page, Model model) throws Exception{
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);

        List<Sign> recordList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("departmentId",staff.getDepartmentId());
        paramMap.put("querytime",querytime);
        //设置总页数
        pagingVO.setTotalCount(signService.getDepartmentSignCount(paramMap));
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //按页码和用户id查询考勤记录
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            recordList = signService.showSignRecord(paramMap);
        } else {
            pagingVO.setToPageNo(page);
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            recordList = signService.showSignRecord(paramMap);
        }

        model.addAttribute("recordList", recordList);
        model.addAttribute("pagingVO", pagingVO);
        model.addAttribute("querytime", querytime);

        System.out.println(pagingVO.getTotalCount());

        return "/managers/showSignRecord";
    }

    //获取未处理请假单数量
    @RequestMapping(value = "/getUnhandledCount")
    @ResponseBody
    public String getUnhandledCount() throws Exception{
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);

        int departmentId = staff.getDepartmentId();
        int unHandled = leaveService.getUnhandledCount(departmentId);
        String result = String.valueOf(unHandled);

        return result;
    }

    //查询请假记录及状态
    @RequestMapping(value = "/leaveRecord")
    public String leaveRecordShow(Model model, Integer page) throws Exception {
        //获取当前用户id
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getPrincipal();
        Staff staff = staffService.findById(id);

        int departmentId = staff.getDepartmentId();
        List<Leave> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(leaveService.getDepartmentLeaveCount(departmentId));
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            //按页码和用户id查询请假记录
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("departmentId",staff.getDepartmentId());
            list = leaveService.findByPagingD(paramMap);
        } else {
            pagingVO.setToPageNo(page);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("toPageNo", pagingVO.getTopageNo());
            paramMap.put("pageSize", pagingVO.getPageSize());
            paramMap.put("departmentId",staff.getDepartmentId());
            list = leaveService.findByPagingD(paramMap);
        }

        model.addAttribute("leaveList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/managers/leaveRecord";
    }

    // 显示请假申请详细信息
    @RequestMapping(value = "/leaveDetail")
    public String gradeCourse(Integer recordId, Model model) throws Exception {
        if (recordId == null) {
            return "";
        }
        Leave leaveDetail= leaveService.findByRecordId(recordId);
        model.addAttribute("leaveDetail", leaveDetail);
        return "/managers/showLeaveDetail";
    }

    //更新请假单审核信息
    @RequestMapping(value="/handleLeave")
    @ResponseBody
    public boolean handleLeave(String reply,String result,Integer recordId) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("recordId",recordId);
        paramMap.put("reply",reply);
        paramMap.put("result",result);
        leaveService.handleLeave(paramMap);
        return true;
    }

}
