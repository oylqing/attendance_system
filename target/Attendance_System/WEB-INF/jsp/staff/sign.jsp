<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>签到签退</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/digit.js"></script>
	<script src="<%=request.getContextPath()%>/js/time.js"></script>

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
					    	<h1 class="col-md-5">签到签退</h1>
						</div>
				    </div>
					<canvas id="canvas"></canvas>
					<p>
					<button id="sign_in" type="button" class="btn btn-primary btn-lg" style="margin-left: 50px;">签  到</button>
					<button id="sign_out" type="button" class="btn btn-primary btn-lg" style="margin-left: 260px">签  退</button>
					</p>
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
    $("#nav li:nth-child(2)").addClass("active");
	$("#sign_in").click(function () {
        var url = "<%=request.getContextPath()%>/staff/signIn";
        $.post(url,function (data) {
            alert("签到成功\n"+"签到时间："+data);
        });

	});
    $("#sign_out").click(function () {
        var url = "<%=request.getContextPath()%>/staff/signOut";
        $.post(url,function (data) {
            alert(data);
        });
    });
</script>

</html>