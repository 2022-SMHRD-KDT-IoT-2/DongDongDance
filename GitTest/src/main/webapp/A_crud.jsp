<%@page import="com.model.AreaVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.controller.AreaDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	INSERT
	<form action="A_joincon" method="post">
		<li><input type="text" name="area_i_id"
			placeholder="area_id을 입력하세요"></li>
		<li><input type="text" name="area_i_name"
			placeholder="area_name를 입력하세요"></li>
		<li><input type="submit" value="삽입!!!"></li> 
	</form>

	DELETE
	<form action="A_delete" method="post">
		<li><input type="text" name="area_d_id"
			placeholder="area_id을 입력하세요"></li>
		<li><input type="submit" value="삭제!!!"></li>
	</form>

	UPDATE
	<form action="A_update" method="post">
		<li><input type="text" name="area_u_tid" placeholder="ID 을 입력하세요"></li>
		<li><input type="text" name="area_u_id"
			placeholder="변경 ID 을 입력하세요"></li>
		<li><input type="text" name="area_u_name"
			placeholder="변경 name 을 입력하세요"></li>
		<li><input type="text" name="area_u_status"
			placeholder="변경 state를 입력하세요"></li>
		<li><input type="submit" value="제출!!!"></li>
	</form>

	GETLIST
		<%
			AreaDAO dao = new AreaDAO();
			ArrayList<AreaVO> al = dao.getList();
			System.out.print(al.size());
			String st = "<table border = '1'>";
			for (int i = 0; i < al.size(); i++) {
				st+= "<td>"+ al.get(i).getAreaId()+"</td>";
				st+= "<td>"+ al.get(i).getAreaName()+"</td>";
				st+= "<td>"+ al.get(i).getAreaStatus()+"</td></tr>";
			}
			st += "</table>";
			%>
			<%=st %>
	

</body>
</html>