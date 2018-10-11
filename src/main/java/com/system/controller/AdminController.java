package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.pojo.*;
import com.system.service.AdminService;
import com.system.service.DepartmentService;
import com.system.service.LoginService;
import com.system.service.StaffCustomService;
import com.system.utils.DateJsonValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "staffCustomServiceImpl")
    private StaffCustomService staffCustomService;

    @Resource(name = "departmentServiceImpl")
    private DepartmentService departmentService;

    @Resource(name = "loginServiceImpl")
    private LoginService loginService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    //  员工信息显示
    @RequestMapping("/showStaff")
    public String showStudent(Model model, Integer page) throws Exception {

        List<StaffCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(staffCustomService.getStaffCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = staffCustomService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = staffCustomService.findByPaging(page);
        }

        model.addAttribute("staffList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "/admin/showStaff";

    }

    //显示员工详细信息
    @RequestMapping("/showStaffDetail")
    public String showStaffDetail(String staffId, Model model) throws Exception{
        StaffCustom staffDetail = staffCustomService.findById(staffId);
        model.addAttribute("staffDetail", staffDetail);

        List<Department> list = departmentService.finAll();
        model.addAttribute("departmentList", list);
        return "/admin/showStaffDetail";
    }

    //  添加员工信息页面显示
    @RequestMapping(value = "/addStaff", method = {RequestMethod.GET})
    public String addStudentUI(Model model) throws Exception {

        List<Department> list = departmentService.finAll();

        model.addAttribute("departmentList", list);

        return "/admin/addStaff";
    }

     // 添加员工信息操作
    @RequestMapping(value = "/addStaff", method = {RequestMethod.POST})
    public String addStudent(StaffCustom staffCustom, Model model) throws Exception {

        /*System.out.println(staffCustom.getRole());*/
        Boolean result = staffCustomService.save(staffCustom);

        if (!result) {
            model.addAttribute("message", "员工编号重复");
            return "/error";
        }
        //添加成功后，也添加到登录表

        //重定向
        return "redirect:/admin/showStaff";
    }


    // 修改员工信息处理
    @RequestMapping(value = "/editStaff", method = {RequestMethod.POST})
    @ResponseBody
    public boolean editStaff(int role,int department,String staffId) throws Exception {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("staffId",staffId);
        paramMap.put("department",department);
        paramMap.put("role",role);

        staffCustomService.editStaff(paramMap);
        return true;
    }

    // 删除员工
    @RequestMapping(value = "/removeStaff"/*, method = {RequestMethod.GET} */)
    public String removeStaff(String id) throws Exception {
        if (id == null) {
            //加入没有带员工id就进来的话就返回员工显示页面
            return "/admin/showStaff";
        }
        staffCustomService.removeById(id);
        loginService.removeById(id);

        return "redirect:/admin/showStaff";
    }

    // 搜索员工
    @RequestMapping(value = "/selectStaff")
    public String selectStaff(String findByName, Model model) throws Exception {

        List<StaffCustom> list = staffCustomService.findByName(findByName);

        model.addAttribute("staffList", list);
        return "/admin/showStaff";
    }

    // 考勤记录统计查询页面显示
    @RequestMapping(value = "/showSign")
    public String showSign() throws Exception {
        return "/admin/showSign";
    }

    // 考勤记录统计查询
    @RequestMapping(value = "/loadReportInfoData")
    public String loadReportInfoData(Model model,ReportInfo reportInfo) throws Exception {
        List<ReportInfo> reportInfoList = new ArrayList<ReportInfo>();
        try {
            reportInfoList = adminService.loadReportInfoData(reportInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("reportInfoList", reportInfoList);

        return "/admin/showSign";
    }

    //考勤记录详细信息显示
    @RequestMapping(value = "/loadReportDetailData")
    public void loadReportDetailData(PrintWriter printWriter,String reportId, Model model) throws Exception {
        List<ReportDetail> reportDetailList = new ArrayList<ReportDetail>();

        try {
            reportDetailList = adminService.loadReportDetailData(reportId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*model.addAttribute("reportDetailList", reportDetailList);*/
        /*System.out.println(reportDetailList.size());*/
        String resultJson = JSON.toJSONString(reportDetailList);
        printWriter.write(resultJson);
        printWriter.flush();
        printWriter.close();
    }

    // 请假记录查询页面显示
    @RequestMapping(value = "/showLeave")
    public String showLeave() throws Exception {
        return "/admin/showLeave";
    }

    // 请假记录查询页面显示
    @RequestMapping(value = "/showLeaveRecord")
    @ResponseBody
    public Map<String, Object> showLeaveRecord() throws Exception {
        List<Leave> leaveList = new ArrayList<Leave>();
        try {
            leaveList = adminService.loadLeaveList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray jarray = JSONArray.fromObject(leaveList,jsonConfig);


        for (int i = 0; i < jarray.size(); i++) {
            JSONObject json=new JSONObject();
            json=jarray.getJSONObject(i);
            if(json.get("handleTime")==null||!"".equals(json.get("handleTime"))){//因为在添加的时候只有这个值可能为NULL所以我就判断了这个。
                json.put("handleTime", "--");
                jarray.set(i,json);
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if(leaveList != null) {
            map.put("total", jarray.size());
            map.put("rows", jarray);
        }
        /*String resultJson = JSON.toJSONString(leaveList);
        System.out.println(resultJson);*/

        return map;
    }

    // 本账户密码重置
    @RequestMapping("/passwordReset")
    public String passwordRestUI() throws Exception {
        return "/admin/passwordReset";
    }


}
