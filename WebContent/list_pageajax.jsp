<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有学生信息ajax</title>

<script type="text/javascript">
	function doDelete(sid){
		var flag = confirm("是否确定删除？");
		if( flag ){
			location.href = "DeleteServlet?sid=" + sid;
		}
	}
</script>

</head>
<body>
	<h3>欢迎使用学生管理系统</h3>
	<form id="pageStudent" action="SearchStudentServlet" method="post">
	<table border="1" width="700">
		<tr colspan="8">
			<td colspan="8">
					按姓名查询:<input onchange="listPage(1)" id="sname" type="text" name="sname"/>
					&nbsp;
					按性别查询:<select onchange="listPage(1)" id="sgender" name="sgender">
								<option value="">--请选择--
								<option value="男">男
								<option value="女">女
							  </select>
					&nbsp;&nbsp;&nbsp;
					<!-- <input type="submit" value="查询"> -->
					&nbsp;&nbsp;&nbsp;
					<a href="add.jsp">添加</a>
				</td>
		</tr>
		<tr id="data_before" colspan="8" align="center">
			<td>编号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>电话</td>
			<td>生日</td>
			<td>爱好</td>
			<td>简介</td>
			<td>操作</td>
		</tr>
	</table>
	</form>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	<!-- 获取数据的方法 -->
	function listPage(currentPage){
		var name = $("#sname").val();
		var sex = $("#sgender").val();
		$.post("AjaxStudentListPageServlet",{'name':name,'sex':sex,'currentPage':currentPage},function(data){	
			var data_before_tr = $("#data_before");
			var data_length = data.list.length;
			$(".search").remove();
			$(data.list).each(function(index,c){
				data_before_tr.parent().append("<tr class='search' align='center'><td>"+c.sid+"</td>"+"<td>"+c.sname+"</td>"+"<td>"+c.gender+"</td>"+"<td>"+c.phone+"</td>"+"<td>"+c.birthday+"</td>"+"<td>"+c.hobby+"</td>"+"<td>"+c.info+"</td><td align='center'><a href='EditServlet?su="+c.sid+"'>更新</a>&nbsp;&nbsp;<a href='#' onclick='doDelete("+c.sid+")'>删除</a></td></tr>");
			});
			
			sliptPage(data,"listPage",data_before_tr.parent());
			
		},"json");
	}
	listPage(1);
	
	function doDelete(sid){
		var flag = confirm("是否确定删除？");
		if( flag ){
			location.href = "DeleteServlet?sid=" + sid;
		}
	}
	
	function sliptPage(data,fun,parent){
		var str = "";
		for(var i=1; i<=data.totalPage; i++){
			if(i==data.currentPage){
				str += "&nbsp;&nbsp;<a style='text-decoration:none;color:red' href='#' onclick='"+fun+"("+i+")'>"+i+"</a>&nbsp;&nbsp;";
				continue;
			}
			str += "&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+i+")'>"+i+"</a>&nbsp;&nbsp;";
		}    
		
		if(data.currentPage == 1 && data.totalPage == 1){
			str = "<tr class='search'><td colspan='8'>"+"第 "+data.currentPage+"/"+data.totalPage+"&nbsp;&nbsp;&nbsp;每页显示"+data.pageSize+"条"+"&nbsp;&nbsp;&nbsp;&nbsp;总的记录数&nbsp;"+data.totalSize+"</td></tr>";
		}else if(data.currentPage == 1 && data.totalPage != 1 ){
			str = "<tr class='search'><td colspan='8'>"+"第 "+data.currentPage+"/"+data.totalPage+"&nbsp;&nbsp;&nbsp;每页显示"+data.pageSize+"条"+"&nbsp;&nbsp;&nbsp;&nbsp;总的记录数&nbsp;"+data.totalSize+str+"&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+(data.currentPage+1)+")'>下一页</a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+data.totalPage+")'>尾页</a>"+"</td></tr>"
		}else if(data.currentPage != data.totalPage){
			str = "<tr class='search'><td colspan='8'>"+"第 "+data.currentPage+"/"+data.totalPage+"&nbsp;&nbsp;&nbsp;每页显示"+data.pageSize+"条"+"&nbsp;&nbsp;&nbsp;&nbsp;总的记录数&nbsp;"+data.totalSize+"&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+1+")'>首页</a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+(data.currentPage-1)+")'>上一页</a>"+str+"&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+(data.currentPage+1)+")'>下一页</a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+data.totalPage+")'>尾页</a>"+"</td></tr>"
		}else if(data.currentPage == data.totalPage ){
			str = "<tr class='search'><td colspan='8'>"+"第 "+data.currentPage+"/"+data.totalPage+"&nbsp;&nbsp;&nbsp;每页显示"+data.pageSize+"条"+"&nbsp;&nbsp;&nbsp;&nbsp;总的记录数&nbsp;"+data.totalSize+"&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+1+")'>首页</a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='"+fun+"("+(data.currentPage-1)+")'>上一页</a>"+str+"</td></tr>";
		}
		parent.append(str);
	}
</script>


</body>

</html>