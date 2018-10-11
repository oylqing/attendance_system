<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=request.getContextPath()%>/staff/showMassage">个人信息<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/staff/sign">签到签退<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/staff/signRecord">考勤记录<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/staff/leave">请假<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/staff/leaveRecord">请假记录查询<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/staff/passwordReset">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="<%=request.getContextPath()%>/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>