<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>请假申请</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/daterangepicker-bs3.css" />
	<!-- 引入JQuery  bootstrap.js-->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

	<script src="<%=request.getContextPath()%>/js/moment.js"></script>
	<script src="<%=request.getContextPath()%>/js/daterangepicker.js"></script>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">请假申请</h1>
						</div>
				    </div>
					<div class="panel-body">
						<div class="well">
							<form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/staff/handinLeave" id="leave" method="post" onsubmit="return check()">
								<fieldset>
									<div class="form-group">
										<div class="control-group">
											<label class="control-label" for="leavetime">选择请假时间：</label>
											<div class="controls">
												<div class="input-prepend input-group">
													<span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
													<input type="text" style="width: 600px" name="leavetime" id="leavetime" class="form-control span4" placeholder="请选择请假时间"/>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="control-group">
											<label for="reason" class="control-label">请假原因：</label>
											<div class="controls">
												<div class="input-prepend input-group">
													<input type="text" style="height: 100px;width: 640px" name="reason" class="form-control" id="reason" placeholder="请输入请假原因">
												</div>
											</div>
										</div>
									</div>
									<div class="form-group" style="text-align: center">
										<button class="btn btn-default" type="submit">提交</button>
										<button class="btn btn-default">重置</button>
									</div>
								</fieldset>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>

<script type="text/javascript">
    $("#nav li:nth-child(4)").addClass("active");
    $(document).ready(function() {
        $('#leavetime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            format: 'MM/DD/YYYY HH:mm A'
        }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });

    function check() {
        if(leave.leavetime.value==""||leave.leavetime.value==null)
        {alert("请选择请假时间！");return false;}
        if(leave.reason.value==""||leave.reason.value==null)
        {alert("请输入请假原因！");return false;}
    }
</script>

</html>