<%@page import="com.VO.EmployeeVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DAO.AreaDAO"%>
<%@page import="com.DAO.RfidDAO"%>
<%@page import="com.DAO.EmployeeDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Mng_emp</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<!-- CSS only -->
	<style>
		.container {
			display: grid;
			grid-template-columns: repeat(3, 1fr);
			grid-gap: 30px;
			grid-auto-rows: minmax(100px, auto);
			justify-items: center;
		}

	</style>
</head>

<body class="is-preload">
<%
EmployeeDAO eDao = new EmployeeDAO();
RfidDAO rDao = new RfidDAO();
AreaDAO aDao = new AreaDAO();

ArrayList<EmployeeVO> al = eDao.selectAll();

%>
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<strong>사원관리</strong>
					<ul class="icons">
						<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a>
						</li>
						<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
					</ul>
				</header>

				<!-- Content -->
				<section class="py-5">
					<div class="container">


                                        <%
            
                                          for(int i=0; i<al.size(); i++){
                                             out.print("<div>");
                                             out.print("<div>");
                                             out.print("<img src=\"assets/emp_img/emp"+(i+1)+".png\" alt=\"...\" height=\"250px\" width=\"250px\">");
                                             out.print("<div>");
                                             out.print("<div>");
                                             out.print("<div class=\"name\">");
                                             out.print("<h5>"+al.get(i).getEmpName()+"</h5>");
                                             out.print("</div>");
                                             out.print("<div class=\"info\">");
                                             out.print("<p> 연락처 : "+al.get(i).getEmpPhone()+"</p>");
                                             out.print("<p> 직급 : "+eDao.selectPo(al.get(i).getEmpId())+"</p>");
                                             out.print("<p> 입사일 : "+al.get(i).getEmpJoindate()+"</p>");
                                             out.print("<p> 현재상태 : "+rDao.selectType(al.get(i).getEmpId())+"</p>");
                                             out.print("</div>");
                                             out.print("</div>");
                                             out.print("</div>");
                                             out.print("</div>");     
                                             out.print("</div>");     
                                          }

                                          %>

				</section>

			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search -->
				<section id="search" class="alt">
					<form method="post" action="#">
						<input type="text" name="query" id="query" placeholder="Search" />
					</form>
				</section>

				<!-- Menu -->
				<nav id="menu">
					<header class="major">
						<h2>Menu</h2>
					</header>
					<ul>
                        <li><a href="main.jsp">Home</a></li>
                        <li><a href="state.jsp">근태확인</a></li>
                        <li><a href="monitoring.jsp">모니터링</a></li>
                        <li><a href="cnt_light.jsp">조명관리</a></li>
                        <li><a href="mng_emp.jsp">사원관리</a></li>
                        <li><a href="management.jsp">기능관리</a></li>
                        <li><a href="ck_elec.jsp">전기사용량</a></li>
					</ul>
				</nav>

				<!-- Section -->


				<!-- Section -->


				<!-- Footer -->
				<footer id="footer">
					<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a
							href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5
							UP</a>.</p>
				</footer>

			</div>
		</div>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>