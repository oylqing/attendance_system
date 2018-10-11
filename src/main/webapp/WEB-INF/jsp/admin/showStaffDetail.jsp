<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>员工详细信息</title>

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
					    	<h1 class="col-md-5">员工详细信息</h1>
						</div>
				    </div>

					<table class="table table-bordered">
						<tr>
							<td>员工编号：</td>
							<td>${staffDetail.staffId}</td>
							<td>员工姓名：</td>
							<td>${staffDetail.staffName}</td>
						</tr>
						<tr>
							<td>部门：</td>
							<td>
							<select class="form-control" name="departmentId" id="department" style="width: 60%; height: 100%">
								<c:forEach items="${departmentList}" var="item">
									<option value="${item.departmentId}">${item.departmentName}</option>
								</c:forEach>
							</select>
							</td>
							<%--<td>${staffDetail.departmentName}</td>--%>
							<td>职位：</td>
							<td>
								<select class="form-control" name="role" id="role" style="width: 60%; height: 100%">
									<option value="1">manager</option>
									<option value="2">staff</option>
								</select>
							</td>
							<%--<td>${staffDetail.roleName}</td>--%>
						</tr>
						<tr>
							<td>性别：</td>
							<td>${staffDetail.sex}</td>
							<td>生日：</td>
							<td><fmt:formatDate value="${staffDetail.birthday}" pattern="yyyy年MM月dd日" /></td>
						</tr>
						<tr>
							<td>入职时间：</td>
							<td><fmt:formatDate value="${staffDetail.grade}" pattern="yyyy年MM月dd日" /></td>
							<td>联系电话：</td>
							<td>${staffDetail.tel}</td>
						</tr>
						<tr>
							<td>邮箱：</td>
							<td>${staffDetail.email}</td>
							<td>爱好：</td>
							<td>${staffDetail.hobby}</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center">
							<button class="btn btn-primary" type="submit" id="edit">提交修改</button>
							<a class="btn btn-primary" type="button" href="<%=request.getContextPath()%>/admin/showStaff">返回</a>
							</td>
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
    $("#nav li:nth-child(1)").addClass("active");

    document.getElementById("department").value = "${staffDetail.departmentId}";
    document.getElementById("role").value = "${staffDetail.role}";

    $("#edit").click(function () {
        var url = "<%=request.getContextPath()%>/admin/editStaff";
        var department = $("#department option:selected").val();
        var role = $("#role option:selected").val();
        var staffId= ${staffDetail.staffId};
        $.post(url,{department:department,role:role,staffId:staffId},function (data) {
            if(data) window.location.href = "<%=request.getContextPath()%>/admin/showStaff";
        });
    });

</script>

</html>