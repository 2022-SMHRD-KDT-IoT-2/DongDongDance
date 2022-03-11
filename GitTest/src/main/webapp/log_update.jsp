<%@page import="com.VO.EmployeeVO"%>
<%@page import="com.VO.RfidVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DAO.AreaDAO"%>
<%@page import="com.DAO.RfidDAO"%>
<%@page import="com.DAO.EmployeeDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
    <title>근태이력 수정</title>
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
  int seq = Integer.parseInt(request.getParameter("seq"));
 
  RfidDAO dao = new RfidDAO();
  RfidVO vo = dao.selectLog(seq);

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
                    <strong>근태이력 수정</strong>
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
                    <h2>수정사항 입력 (근태이력 번호 : <%=seq %>)</h2>
                    <form method="post" action="UpdateLogCon.do">
                        <div class="row gtr-uniform">
                            <input type="hidden" name="seq" id="seq" value="<%=seq%>">
                            <div class="col-6">
                            <span style="font-size:0.75em;">□ 근태기록 수정 (현재 기록 : <%=vo.getLogType() %>)</span>
                                <select name="type" id="type">
                                    <option value="<%=vo.getLogType()%>">근태타입 선택</option>
                                    <option value="0">퇴근</option>
                                    <option value="1">출근</option>
                                    <option value="2">지각</option>
                                    <option value="3">조퇴</option>
                                    <option value="4">주말출근</option>
                                    <option value="5">주말퇴근</option>
                                    <option value="6">출근취소</option>
                                    <option value="7">결근</option>
                                    <option value="8">점심</option>
                                    <option value="9">복귀</option>
                                    <option value="A">추가근무</option>
                                </select>
                            </div>
                            
                            <div class="col-12">
                                <span style="font-size:0.75em;">□ 특이사항 입력 (현재 기록 : <%=vo.getLogMemo() %>)</span>
                                <textarea name="memo" id="memo" placeholder="특이 사항을 입력하세요.(특이사항이 없을 경우 n차 수정이라는 값을 입력하셔야 합니다.)" rows="6"></textarea>
                            </div>
                            <div class="col-12">
                                <ul class="actions">
                                    <li><input type="submit" value="수정" class="primary" /></li>
                                    <li><input type="reset" value="리셋" /></li>
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