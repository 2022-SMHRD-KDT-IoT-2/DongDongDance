<%@page import="com.DAO.PlugSenDAO"%>
<%@page import="com.VO.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>전기사용량확인</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<style>
.logout {
	text-decoration: none;
	float: right;
	border-block: 0;
}
</style>
</head>
<body class="is-preload">
 <%
   EmployeeVO evo = (EmployeeVO)session.getAttribute("loginvo");
   String ss = "";
   String sq = "";

   if(evo != null){
   	if(evo.getEmpStatus().equals("1")){
   		ss = "근무중";
   	}else{
   		ss = "퇴근";
   	}
   	
   	if(evo.getAdminYn().equals("Y")){
   		sq = "관리자";
   	}else{
   		sq = "직원";
   	}
   }

   %>
   	<%
	PlugSenDAO psdao = new PlugSenDAO();
	double m1 = psdao.monthlyPower1();
	double m2 = psdao.monthlyPower2();
	double m3 = psdao.monthlyPower3();
	String ah = "";
	if(m1>m2){
		ah = "증가";
	}else{
		ah = "감소";
	}
	%>
   		<!-- Wrapper -->
   			<div id="wrapper">

   				<!-- Main -->
   					<div id="main">
   						<div class="inner">
   						<div style="position: absolute; right: 16px; top: 5px;">
   					<a class="logout" href="LogoutCon.do">LOGOUT</a>
   					<span style="float: right;"><%=evo.getEmpName()%>(<%=sq%>)[<%=ss%>] /&nbsp;</span>
   				</div>

				<!-- Header -->
				<header id="header">
					<strong>전기사용량</strong>
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
				<section>
					<header class="main">
					</header>
					<div>
						<canvas id="monthly-power-usage-chart"> </canvas>
					</div>
					<br>
					<h2 style="text-align:center">지난 달에 비해 전력사용량이 <%=(int)((m1/m2)*100)-100%>% <%=ah %>했습니다.</h2>
				</section>
				 <ul class="actions">
					 	<li><a href="ck_elec_area.jsp" class="button primary">구역별 조회</a></li>
					 	<li><a href="ck_elec_emp.jsp" class="button primary">직원별 조회</a></li>
			     </ul>

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
	<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"
		integrity="sha256-ErZ09KkZnzjpqcane4SCyyHsKAXMvID9/xwbl/Aq1pc=" crossorigin="anonymous"></script>
	<script src="assets/js/chart.js"></script>
	<script>

		const ctx = document.getElementById('monthly-power-usage-chart').getContext('2d');
		const myChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ['2달전', '1달전', '이번달(현재까지 사용량)'],
				datasets: [{					
					data: [<%=m3%>, <%=m2%>, <%=m1%>],
					backgroundColor: [
						'rgb(236, 255, 214)',
						'rgb(196, 233, 155)',
						'rgb(143, 188, 149)',
					],
					barPercentage: 0.4,
					borderColor: [
						'rgba(236, 255, 214, 1)',
						'rgba(196, 233, 155, 1)',
						'rgba(143, 188, 149, 1)',
					],
					borderWidth: 1
				}]
			},
			options: {
				plugins:{
				legend:{
					display: false,
				}
				},
				scales: {
					y: {
						beginAtZero: true
					}
				}
			}
		});
	/*
		let renewTime = 1000;
		
		setInterval(() => {			
			fetch('/GitTest/Api2').then(response => {
				return response.json();
			}).then(json => {
				changeLastData(myChart, json.value);			
			});

		}, renewTime); */
		
		
	</script>
</body>
</html>