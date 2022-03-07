<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Generic - Editorial by HTML5 UP</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<!-- 도면때문에 style넣음 그냥.. -->
	<style>
		.light-control-borad {
			display: flex;
			flex-direction: column;
			justify-content: space-evenly;
		}

		.light-control-borad li {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-top: 2px;
			width: 120px;
		}

		.light-control-borad p {
			margin: 0;
			padding: 0;

		}

		.light-control-borad label {
			margin: 0;
		}

		.imgroom {
			margin-left: 100px;
		}

		.dep {
			margin-left: 320px
		}

		.light {
			position: absolute;
			background: rgba(255, 255, 0, 0.5);
			border-radius: 100%;
			width: 6%;
			height: 6%;

		}

		.light-off {
			display: none;
		}
	</style>
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<strong>조명관리</strong>
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
					<!-- 본문 시작 -->
					<section>
						<div class="content">
							<header>

								<span class="image object">
									<div class="dep">
										<li>A 부서</li>
									</div>
									<div class="imgroom" style="display: flex;">

										<!-- 조명 켜지는 작업 -->
										<div style="position:sticky">

											<img src="assets/image/light_drawing.png" alt=""
												style="width: 500px; height: 500px;" />


											<div id="light-1" class="light light-off" style="
												top: 7.4%;
												left: 27%;">
											</div>

											<div id="light-2" class="light light-off" style="
											top: 36%;
											left: 12.5%;">
											</div>

											<div id="light-3" class="light light-off" style="
											top: 36%;
											left: 40%;">
											</div>


											<div id="light-4" class="light light-off" style="
											top: 73%;
											left: 22%">
											</div>

											<div id="light-5" class="light light-off" style="
											top: 72%;
											left: 55%">
											</div>

											<div id="light-6" class="light light-off" style="
											top: 21%;
											left: 55.4%;">
											</div>





											<!-- 조명 켜지는 작업 끝 -->
										</div>

										<ul class="light-control-borad">
											<li class="justified_light">
												<p>조명 1</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-1"></span>
												</label>
											</li>

											<li class="justified_light">
												<p>조명 2</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-2"></span>
												</label>
											</li>

											<li class="justified_light">
												<p>조명 3</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-3"></span>
												</label>
											</li>

											<li class="justified_light">
												<p>조명 4</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-4"></span>
												</label>
											</li>

											<li class="justified_light">
												<p>조명 5</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-5"></span>
												</label>
											</li>
											<li class="justified_light">
												<p>조명 6</p>
												<label class="switch-button">
													<input type="checkbox" />
													<span class="onoff-switch" data-light-id="light-6"></span>
												</label>
											</li>
										</ul>
									</div>
								</span>

							</header>




						</div>

					</section>

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


						<div class="searchbox">
							<div class="Sheader">
								<h1 id="searchboxname">장소 검색</h1>
								<input onkeyup="filter()" type="text" id="Svalue" placeholder="Type to Search">
							</div>

							<div class="Scontainer">

								<div class="Sitem">
									<span class="Sicon">A</span>
									<span class="Sname">A부서</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">B</span>
									<span class="Sname">B부서</span>
								</div>

								<div class="Sitem">
									<span class="Sicon">C</span>
									<span class="Sname">C부서</span>
								</div>


							</div>




						</div>
						<!-- 진자서치박스 끝 -->



						<!-- 본문 끝 -->

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

	<script>
		Array.from(document.getElementsByClassName('onoff-switch')).forEach(item => {
			item.onclick = (evt) => {
				const lightId = evt.target.dataset.lightId;
				const light = document.getElementById(lightId);
				if (light.classList.contains("light-off")) {
					light.classList.remove("light-off");
				} else {
					light.classList.add("light-off");
				}
			}
		})
	</script>

</body>

</html>