<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>请假申请详情</title>

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
					    	<h1 class="col-md-5">请假申请详情</h1>
						</div>
				    </div>

					<table class="table table-bordered">
						<tr>
							<td>申请编号：</td>
							<td>${leaveDetail.recordId}</td>
							<td>员工编号：</td>
							<td>${leaveDetail.staffId}</td>
						</tr>
						<tr>
							<td>申请时间：</td>
							<td><fmt:formatDate value="${leaveDetail.haninTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
							<td>申请状态：</td>
							<c:if test="${leaveDetail.applicationState==0}" ><td style="color: orange">待审核</td></c:if>
							<c:if test="${leaveDetail.applicationState==1}" ><td style="color: green">通过</td></c:if>
							<c:if test="${leaveDetail.applicationState==2}" ><td style="color: red">不通过</td></c:if>
						</tr>
						<tr>
							<td>开始时间：</td>
							<td><fmt:formatDate value="${leaveDetail.leaveStartTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
							<td>结束时间：</td>
							<td><fmt:formatDate value="${leaveDetail.leaveEndTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
						</tr>
						<tr>
							<td>请假原因：</td>
							<td colspan="3">${leaveDetail.reason}</td>
						</tr>
						<tr>
							<td>申请回复：</td>
							<td colspan="3">${leaveDetail.reply}</td>
						</tr>
						<tr>
							<td>回复时间：</td>
							<td colspan="3"><fmt:formatDate value="${leaveDetail.handleTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
						</tr>
					</table>
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
    $("#nav li:nth-child(5)").addClass("active")
</script>

</html>