<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>个人信息</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>


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
							<h1 class="col-md-5">个人信息</h1>
						</div>
					</div>
					<div class="panel-body">
						<form name="updateMassage" class="form-horizontal" role="form" action="<%=request.getContextPath()%>/staff/showMassage" id="editfrom" method="post" onsubmit="return check()">
							<div class="form-group ">
								<label for="staffId" class="col-sm-2 control-label" >员工编号</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="staffId" name="staffId" value="${staff.staffId}">
								</div>
								<label for="staffName" class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="staffName" name="staffName"  value="${staff.staffName}">
								</div>
							</div>
							<div class="form-group">
								<label for="sex" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="sex" name="sex"  value="${staff.sex}">
								</div>
								<label for="birthday" class="col-sm-2 control-label">生日</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="birthday" name="birthday"  value="<fmt:formatDate value="${staff.birthday}" pattern="yyyy-MM-dd" />">
								</div>
							</div>
							<div class="form-group">
								<label for="grade" class="col-sm-2 control-label" >入职时间</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="grade" name="grade"  value="<fmt:formatDate value="${staff.grade}" pattern="yyyy-MM-dd" />">
								</div>
								<label for="department" class="col-sm-2 control-label">所属部门</label>
								<div class="col-sm-4">
									<input readonly="readonly" type="text" class="form-control" id="department" name="departmentId"  value="${departmentList.get(staff.departmentId).departmentName}">
								</div>
							</div>
							<div class="form-group">
								<label for="tel" class="col-sm-2 control-label">联系电话</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="tel" name="tel" value="${staff.tel}">
								</div>
								<label for="email" class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="email" name="email"  value="${staff.email}">
								</div>
							</div>
							<div class="form-group"><div class="col-sm-2"></div><p id="isTel" style="color: #bd4147;"></p></div>
							<div class="form-group">
								<label for="hobby" class="col-sm-2 control-label">爱好</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="hobby" name="hobby"  value="${staff.hobby}">
								</div>
							</div>
							<div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							</div>
						</form>
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
        $("#nav li:nth-child(1)").addClass("active");
        function check() {
            if(updateMassage.tel.value==""||updateMassage.tel.value==null)
            {alert("请输入联系电话！");$("#tel").focus();return false;}
            if(updateMassage.email.value==""||updateMassage.email.value==null)
            {alert("请输入邮箱！");$("#email").focus();return false;}
            if(updateMassage.hobby.value ==""|| updateMassage.hobby.value==null)
            {alert("请输入爱好！");$("#hobby").focus();return false;}
            //手机号验证
            var tel = $('#tel').val();
            var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!reg.test(tel)) {
                $("#isTel").html("联系电话格式有误");
                $("#tel").focus();
                return false;
            }
        }
	</script>
</html>