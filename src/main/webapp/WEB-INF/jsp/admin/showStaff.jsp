<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>员工信息显示</title>

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
					    	<h1 class="col-md-5">员工名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="<%=request.getContextPath()%>/admin/selectStaff" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入员工姓名" name="findByName">
									<span class="input-group-addon btn" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='<%=request.getContextPath()%>/admin/addStaff'">
								添加员工信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
					                <th>员工编号</th>
				  					<th>姓名</th>
				  					<th>性别</th>
				  					<th>所属部门</th>
				  					<th>职位</th>
				  					<th>入职时间</th>
				  					<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${staffList}" var="item" varStatus="status">
								<tr>
									<td>${item.staffId}</td>
									<td>${item.staffName}</td>
									<td>${item.sex}</td>
									<td>${item.departmentName}</td>
									<td>${item.roleName}</td>
									<td><fmt:formatDate value="${item.grade}" dateStyle="medium" /></td>

									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='<%=request.getContextPath()%>/admin/showStaffDetail?staffId=${item.staffId}'">详细</button>
										<button id="${status.index}" class="btn btn-default btn-xs btn-danger btn-primary" onClick="confirmd(${status.index})" >删除</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a href="<%=request.getContextPath()%>/admin/showStaff?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
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

        function confirmd(num) {
            var staffId = $("#"+num).parent().parent().find("td").eq(0).text();

            var msg = "您真的确定要删除吗？";
            if (confirm(msg)==true){
                window.location.href='<%=request.getContextPath()%>/admin/removeStaff?id='+staffId;
            }else{
                return false;
            }
        }

        /*$("#sub").click(function () {
            $("#form1").submit();
        });*/

        <c:if test="${pagingVO != null}">
			if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
				$(".pagination li:last-child").addClass("disabled")
			}

			if (${pagingVO.curentPageNo} == ${1}) {
				$(".pagination li:nth-child(1)").addClass("disabled")
			}
        </c:if>
	</script>
</html>