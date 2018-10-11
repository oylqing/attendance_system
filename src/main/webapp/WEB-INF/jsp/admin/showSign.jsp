<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>考勤记录统计</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/daterangepicker-bs3.css" />
	<!-- 引入JQuery  bootstrap.js-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

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
					    	<h1 class="col-md-4">考勤记录统计</h1>
							<form class="form-horizontal" style="margin: 20px 0 10px 0;" role="form" action="<%=request.getContextPath()%>/admin/loadReportInfoData" id="querySign" method="post">
								<div class="form-group">
									<label for="createDate" class="col-sm-1 control-label">时间</label>
									<div class="col-sm-3">
										<div class="control-group">
											<div class="controls">
												<div class="input-prepend input-group">
													<span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
													<input type="text" name="createDate" id="createDate" class="form-control span4 text-center" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>" placeholder="请选择查询时间"/>
												</div>
											</div>
										</div>
									</div>
									<label for="departmentId" class="col-sm-1 control-label">部门</label>
									<div class="col-sm-2">
										<select id="departmentId" name="departmentId" class="form-control">
											<option value="0">全部</option>
											<option value="1">人事部</option>
											<option value="2">技术部</option>
										</select>
									</div>
									<button class="btn btn-primary" type="submit">查询</button>
								</div>
							</form>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>统计日期</th>
					                <th>部门</th>
				  					<th>日迟到人次</th>
				  					<th>日早退人次</th>
				  					<th>月迟到人次</th>
				  					<th>月早退人次</th>
				  					<th>年迟到人次</th>
				  					<th>年早退人次</th>
									<th>详情</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${reportInfoList}" var="item">
								<tr>
									<td><fmt:formatDate value="${item.createDate}" dateStyle="medium" /></td>
									<td>${item.departmentName}</td>
									<td>${item.dayLateCount}</td>
									<td>${item.dayEarlyCount}</td>
									<td>${item.monthLateCount}</td>
									<td>${item.monthEarlyCount}</td>
									<td>${item.yearLateCount}</td>
									<td>${item.yearEarlyCount}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='javascript:showDetail(${item.reportId})'">详细</button>
										<!--弹出框-->
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>


					<div class="modal fade" id="reportDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">

									</h4>
								</div>
								<div class="modal-body">
									<table class="table table-bordered">
										<thead>
										<tr>
											<th>员工编号</th>
											<th>员工姓名</th>
											<th>日迟到次数</th>
											<th>日早退次数</th>
											<th>月迟到次数</th>
											<th>月早退次数</th>
											<th>年迟到次数</th>
											<th>年早退次数</th>
										</tr>
										</thead>
										<tbody id="reportDetailGrid">
										<%--<c:forEach  items="${reportDetailList}" var="item">
											<tr>
												<td>${item.staffId}</td>
												<td>${item.staffName}</td>
												<td>${item.dayLateCount}</td>
												<td>${item.dayEarlyCount}</td>
												<td>${item.monthLateCount}</td>
												<td>${item.monthEarlyCount}</td>
												<td>${item.yearLateCount}</td>
												<td>${item.yearEarlyCount}</td>
											</tr>
										</c:forEach>--%>
										</tbody>
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
								</div>
							</div><!-- /.modal-content -->
						</div><!-- /.modal -->
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
		$("#nav li:nth-child(2)").addClass("active");

        //时间选择
        $(document).ready(function() {
            $('#createDate').daterangepicker({
                singleDatePicker: true,
                format: 'YYYY-MM-DD'
            }, function(start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
        });

        function showDetail(reportId) {
            var url = '<%=request.getContextPath()%>/admin/loadReportDetailData';
            $.ajax({url:url,type:"POST",data:{reportId:reportId},dataType:"json",success:function (data) {
                    /*for(var p in data){dataInfo(data[p]);}*/

					$('#reportDetail').modal('show');
                    $.each(data, function(idx,obj){dataInfo(obj);});
                    },
                    /*$('#reportDetailGrid').datagrid(data);},*/
				error: function () {
                    alert('获取数据失败');
                }
            });
        }
        function dataInfo(obj) {
            var detail ='<tr>'+
                '<td>'+obj.staffId+'</td>'+
                '<td>'+obj.staffName+'</td>'+
                '<td>'+obj.dayLateCount+'</td>'+
                '<td>'+obj.dayEarlyCount+'</td>'+
                '<td>'+obj.monthLateCount+'</td>'+
                '<td>'+obj.monthEarlyCount+'</td>'+
                '<td>'+obj.yearLateCount+'</td>'+
                '<td>'+obj.yearEarlyCount+'</td>'+
                '</tr>';
            $("#reportDetailGrid").append(detail);
        }



	</script>
</html>