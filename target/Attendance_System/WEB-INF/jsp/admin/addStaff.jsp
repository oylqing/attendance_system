<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>添加员工</title>

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
					    	<h1 class="col-md-5">添加员工信息</h1>
						</div>
				    </div>
					<div class="panel-body">
						<form name="updateMassage" class="form-horizontal" role="form" action="<%=request.getContextPath()%>/admin/addStaff" id="addfrom" method="post">
							<div class="form-group ">
								<label for="staffId" class="col-sm-2 control-label" >员工编号</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="staffId" name="staffId" required>
								</div>
								<label for="staffName" class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="staffName" name="staffName" required>
								</div>
							</div>
							<div class="form-group">
								<label for="departmentId" class="col-sm-2 control-label">所属部门</label>
								<div class="col-sm-4">
									<select class="form-control" name="departmentId" id="departmentId">
										<c:forEach items="${departmentList}" var="item">
											<option value="${item.departmentId}">${item.departmentName}</option>
										</c:forEach>
									</select>
								</div>
								<label for="role" class="col-sm-2 control-label">职位</label>
								<div class="col-sm-4">
									<select class="form-control" name="role" id="role">
										<option value="1">manager</option>
										<option value="2">staff</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-2 control-label">性别</label>
								<div class="col-sm-4">
									<label class="checkbox-inline">
										<input type="radio" name="sex" value="男" checked>男
									</label>
									<label class="checkbox-inline">
										<input type="radio" name="sex" value="女">女
									</label>
								</div>
								<label for="tel" class="col-sm-2 control-label">联系电话</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="tel" name="tel">
								</div>
							</div>
							<div class="form-group">
								<label for="birthday" class="col-sm-2 control-label">生日</label>
								<div class="col-sm-4">
									<div class="control-group">
										<div class="controls">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
												<input type="text" name="birthday" id="birthday" class="form-control span4 text-center" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>" placeholder="请输入出生日期"/>
											</div>
										</div>
									</div>
								</div>
								<label for="grade" class="col-sm-2 control-label">入职时间</label>
								<div class="col-sm-4">
									<div class="control-group">
										<div class="controls">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
												<input type="text" name="grade" id="grade" class="form-control span4 text-center" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>" placeholder="请选择入职时间"/>
											</div>
										</div>
									</div>
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

    //时间选择
    $(document).ready(function() {
        $('#birthday').daterangepicker({
            singleDatePicker: true,
            format: 'YYYY-MM-DD'
        }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
        $('#grade').daterangepicker({
            singleDatePicker: true,
            format: 'YYYY-MM-DD'
        }, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });

</script>

</html>