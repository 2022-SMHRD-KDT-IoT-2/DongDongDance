<%@page import="com.VO.AreaVO"%>
<%@page import="com.DAO.AreaDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
    <title>구역 정보수정</title>
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
 String id = request.getParameter("id");
 AreaDAO aDAO = new AreaDAO();
 id = "EA01";
 AreaVO vo = aDAO.getOne(id);
 
   %>
    <!-- Wrapper -->
    <div id="wrapper">

        <!-- Main -->
        <div id="main">
            <div class="inner">
            <div><a class="logout" href="#">LOGOUT</a></div>

                <!-- Header -->
                <header id="header">
                    <strong>구역 정보수정</strong>
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

                <!-- Form -->
                <section>
                    <h2>세부정보 입력 (구역명 : <%=vo.getAreaName()%>)</h2>
                    <form method="post" action="UpdateAreaCon.do">
                        <div class="row gtr-uniform">
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ 구역의 코드입력(앞 두자리는 방 코드) (현재 코드 : <%=vo.getAreaId()%>)</span>
                                <input type="text" name="id" id="id" value="<%=vo.getAreaId()%>" placeholder="구역의 일련번호(앞 두 자리는 방 번호)" />
                            </div>
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ 구역의 명칭(6자 이내) (현재 명칭 : <%=vo.getAreaName()%>)</span>
                                <input type="text" name="name" id="name" value="<%=vo.getAreaName()%>" placeholder="구역의 명칭(6자 이내)" />
                            </div>
                           
                           <input type="hidden" name="tid" id="tid" value="<%=id%>">
                            
                            <div class="col-12">
                                <ul class="actions">
                                    <li><input type="submit" value="수정" class="primary" /></li>
                                    <li><input type="reset" value="리셋" ></li>
                                </ul>
                            </div>
                        </div>
                    </form>
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