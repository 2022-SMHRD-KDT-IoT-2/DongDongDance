<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Generic - Editorial by HTML5 UP</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<style>
		.swal-button {
			padding-top: 0;
			color: white !important;
			background-color: rgb(197, 197, 197);
		}

		.swal-button:not([disabled]):hover {

			background-color: rgb(143, 188, 149);
		}

		.actions li {
			width: 110px;
		}

		.actions p {
			margin: 0;
			padding: 0;
		}

		.actions label {
			margin: 0;
			padding: 0;
		}

		.justified {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-top: 37px ;
		}
		.location{
			text-align: center;
		}
	</style>
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main" style="width: calc(100vw - 26em);">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<strong>모니터링</strong>
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
						<div class="location">
						<li>장소: 한수진 방</li>
						</div>
					</header>
					<!-- 본문 시작! -->
					<canvas id="myChart" width="1000px" height="350px"></canvas>

					<!-- 스위치 -->
					<ul class="actions" style="display: flex;  justify-content: space-evenly;
						 text-align: center; ">

						<li class="justified">
							<p>연락</p>
							<label class="messageButton">
								<img src="assets/image/greenMessage.png" alt="" width="60px" />
							</label>
						</li>

						<li class="justified">
							<p>제어</p>
							<label class="switch-button">
								<input type="checkbox" />
								<span class="onoff-switch"></span>
							</label>
						</li>
					</ul>


					<!-- 스위치 끝 -->

					<!-- 서브@@@@@@@@@@@ -->
					<input type="checkbox" id="menuicon">
					<ul>
						<li>
							<label for="menuicon">

							</label>
						</li>

					</ul>
					<!-- 서브라이트박스 끝 -->

					<!--진짜 서치박스  -->
					<div class="sidebarRight">
						<!-- 서치박스 -->

						<div class="searchbox">
							<div class="Sheader">
								<h1 id="searchboxname">사원 검색</h1>
								<input onkeyup="filter()" type="text" id="Svalue" placeholder="Type to Search">
							</div>

							<div class="Scontainer">

								<div class="Sitem">
									<span class="Sicon">A</span>
									<span class="Sname">Apple</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">O</span>
									<span class="Sname">Orange</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">M</span>
									<span class="Sname">Mandarin</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">H</span>
									<span class="Sname">한수진</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">J</span>
									<span class="Sname">제동진</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">J</span>
									<span class="Sname">조세정</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">Y</span>
									<span class="Sname">유성준</span>
								</div>

							</div>
						</div>




					</div>
					<!-- 진자서치박스 끝 -->





					<!-- 본문 끝! -->

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

	<script src="assets/js/chart.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"
		integrity="sha256-ErZ09KkZnzjpqcane4SCyyHsKAXMvID9/xwbl/Aq1pc=" crossorigin="anonymous"></script>

	<script>
		let renewTime = 6000;
		let chartId = 'myChart';
		// 칸 구별
		let chartLabels = ['1', '2', '3', '4', '5', '6', '7', '8'];
		let chartDatas = ['10', '15', '20', '25', '30'];
		let chart = chartInit(
			chartId,
			chartLabels,
			chartDatas
		);

		setInterval(() => {
			ㅣ
			// 여기에 값 넣으면 된다!! 6은 db에 시간 테이블
			addData(chart, '6', Math.round((Math.random() * 100)))
		}, renewTime);
	</script>

	<!-- 알림창 -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		document.getElementsByClassName('messageButton')[0].onclick = () => {

			swal({
				title: "전력 이상감지 알림",
				text: "<<한수진 님에게 알림을 보냈습니다."
				//    alert()
			})

		}
	</script>

</body>
</html>