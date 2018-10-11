<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=request.getContextPath()%>/admin/showStaff">员工管理</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/showSign">考勤统计</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/showLeave">请假统计</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/passwordReset">修改密码<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
        <li><a href="<%=request.getContextPath()%>/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>