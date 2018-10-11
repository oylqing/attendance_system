<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>请假记录统计</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <!-- 引入bootstrap-table样式 -->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">

    <!-- 引入JQuery  bootstrap.js-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <!-- bootstrap-table.min.js -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <!-- 引入中文语言包 -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

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
					    	<h1 class="col-md-4">请假记录统计</h1>
						</div>
                        <div id="toolbar"></div>
				    </div>
				    <table id="table"
                           data-toggle="table"
                           data-search="true"
                           data-show-columns="true"
                           data-show-refresh="true"
                           data-show-toggle="true"
                           data-click-to-select="true"
                           data-single-select="true"
                           data-toolbar="#toolbar"
                           <%--data-url="<%=request.getContextPath()%>/admin/showLeaveRecord"--%>>
					        <thead>
					            <tr>
									<th data-field="recordId" data-sortable="true">编号</th>
					                <th data-field="departmentId" data-sortable="true">部门</th>
				  					<th data-field="staffId" data-sortable="true">员工编号</th>
				  					<th data-field="haninTime" data-sortable="true">申请时间</th>
				  					<th data-field="leaveStartTime" data-sortable="true">开始时间</th>
				  					<th data-field="leaveEndTime" data-sortable="true">结束时间</th>
				  					<th data-field="applicationState" data-sortable="true">状态</th>
					            </tr>
					        </thead>
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
		$("#nav li:nth-child(3)").addClass("active");

            $('#table').bootstrapTable({
                url : "<%=request.getContextPath()%>/admin/showLeaveRecord",
                //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据
                //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
                //请求方法
                method: 'get',
                //是否显示行间隔色
                striped: true,
                //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                cache: false,
                //是否显示分页（*）
                pagination: true,
                //是否启用排序
                sortable: true,
                /*//排序方式
                sortOrder: "desc",*/
                //初始化加载第一页，默认第一页
                pageNumber:1,
                //每页的记录行数（*）
                pageSize: 5,
                //可供选择的每页的行数（*）
                pageList: [5, 10, 15, 50],

                queryParamsType:'',
                //默认为空时传递

                //分页方式：client客户端分页，server服务端分页（*）
                sidePagination: "server",
                //是否显示搜索 搜索input name= searchText 服务端获取该值查询即可
                search: true,

                //Enable the strict search.
                strictSearch: true,

                columns: [
                    {
                        field: 'recordId', // 返回json数据中的name
                        title: '编号', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle' // 上下居中
                    }, {
                        field: 'departmentId',
                        title: '部门',
                        align: 'center',
                        valign: 'middle',
                        formatter: function (value, row, index){ // 单元格格式化函数
                            var text = '-';
                            if (value == 1) {
                                text = "人事部";
                            } else if (value == 2) {
                                text = "技术部";
                            }
                            return text;
                        }
                    }, {
                        field: 'staffId', // 返回json数据中的name
                        title: '员工编号', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle' // 上下居中
                    }, {
                        field: 'haninTime', // 返回json数据中的name
                        title: '申请时间', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle' // 上下居中
                    }, {
                        field: 'leaveStartTime', // 返回json数据中的name
                        title: '开始时间', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle' // 上下居中
                    }, {
                        field: 'leaveEndTime', // 返回json数据中的name
                        title: '结束时间', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle' // 上下居中
                    }, {
                        field: 'applicationState', // 返回json数据中的name
                        title: '状态', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle', // 上下居中
                        formatter: function (value, row, index){ // 单元格格式化函数
                            var text = '-';
                            if (value == 0) {
                                text = "待审核";
                            } else if (value == 1) {
                                text = "通过";
                            } else if (value == 2) {
                                text = "不通过";
                            }
                            return text;
                        }

                    }
                ]
            });

	</script>
</html>

