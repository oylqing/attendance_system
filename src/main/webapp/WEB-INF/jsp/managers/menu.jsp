<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="<%=request.getContextPath()%>/managers/showMassage">个人信息<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/managers/showSignRecord">部门考勤记录<span class="badge pull-right"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/managers/leaveRecord">请假申请审批<span class="badge pull-right" id="unHandled"></span></a></li>
        <li><a href="<%=request.getContextPath()%>/logout">退出系统<span class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>

<script>
    $(document).ready(function() {
        $.ajax({
            url:'<%=request.getContextPath()%>/managers/getUnhandledCount',
            type:'POST',
            success:function(result){
            $("#unHandled").text(result);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                               alert(XMLHttpRequest.status);
                                alert(XMLHttpRequest.readyState);
                                alert(textStatus);
            }});

    })
</script>
