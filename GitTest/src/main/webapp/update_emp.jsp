<%@page import="com.VO.AreaVO"%>
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
    <title>사원 정보수정</title>
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
 EmployeeDAO eDAO = new EmployeeDAO();
 AreaDAO aDAO = new AreaDAO();
 ArrayList<EmployeeVO> al1 = eDAO.selectSuper();
 ArrayList<AreaVO> al2 = aDAO.getList();
 EmployeeVO vo = eDAO.selectOne(id);
 
   %>
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
   		<!-- Wrapper -->
   			<div id="wrapper">

   				<!-- Main -->
   					<div id="main">
   						<div class="inner">
   						<div style="position: absolute; right: 16px; top: 5px;">
   					<a class="logout" href="LogoutCon.do">LOGOUT</a>
   					<span style="float: right;"><%=evo.getEmpName()%>(<%=sq%>)[<%=ss%>] /_</span>
   				</div>


                <!-- Header -->
                <header id="header">
                    <strong>사원 정보수정</strong>
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
                    <h2>사원정보 수정 (사원이름 : <%=vo.getEmpName() %>)</h2>
                    <form method="post" action="UpdateEmpCon.do">
                        <div class="row gtr-uniform">
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ 사원 패스워드 수정</span>
                                <input type="password" name="pw" id="pw" value="<%=vo.getEmpPw()%>" placeholder="사원 패스워드 수정" />
                            </div>
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ 사원 이름 수정(현재 이름 : <%=vo.getEmpName() %>)</span>
                                <input type="text" name="name" id="name" value="<%=vo.getEmpName()%>" placeholder="사원 이름 수정" />
                            </div>
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ 사원 좌석번호 수정(현재 이름 : <%=vo.getEmpSeatNo() %>)</span>
                                <input type="text" name="seat" id="seat" value="<%=vo.getEmpSeatNo()%>" placeholder="사원 좌석번호 수정" />
                            </div>
                            
                            <div class="col-6 col-12-xsmall">
                                 <span style="font-size:0.75em;">□ 사원 전화번호 수정(현재 이름 : <%=vo.getEmpPhone() %>)</span>
                                <input type="text" name="phone" id="phone" value="<%=vo.getEmpPhone()%>" placeholder="사원 전화번호 수정" />
                            </div>
                            
                            <div class="col-6 col-12-xsmall">
                                <span style="font-size:0.75em;">□ RFID UID 수정(현재 이름 : <%=vo.getRfidUid() %>)</span>
                                <input type="text" name="rfid" id="rfid" value="<%=vo.getRfidUid()%>" placeholder="RFID UID 수정" />
                            </div>
                            
                            <div class="col-6">
                                <span style="font-size:0.75em;">□ 관리자 여부 수정(현재 : <%=vo.getAdminYn()%>)</span>
                                <select name="yn" id="yn">
                                    <option value="<%=vo.getAdminYn()%>">관리자 여부</option>
                                    <option value="Y">관리자</option>
                                    <option value="N">일반사원</option>
                                </select>
                            </div>
                            
                            <div class="col-6">
                                <span style="font-size:0.75em;">□ 직속상관 선택(현재 : <%=vo.getEmpSuperId()%>)</span>
                                <select name="superid" id="superid">
                                    <option value="<%=vo.getEmpSuperId()%>">직속상관 선택(본인 선택 불가)</option>
                                    <%
                                    for(int i = 0; i<al1.size(); i++){
                                    	out.print("<option value="+al1.get(i).getEmpId()+">"+al1.get(i).getEmpName()+"("+al1.get(i).getEmpId()+")</option>");
                                    }
                                    %>
                                </select>
                            </div>
                            
                            
                            <div class="col-6">
                                <span style="font-size:0.75em;">□ 소속구역 선택(현재 : <%=vo.getAreaId()%>)</span>
                                <select name="area" id="area">
                                    <option value="<%=vo.getAreaId()%>">소속구역 선택</option>
                                    <%
                                    for(int i = 0; i<al2.size(); i++){
                                    	out.print("<option value="+al2.get(i).getAreaId()+">"+al2.get(i).getAreaName()+"("+al2.get(i).getAreaId()+")</option>");
                                    }
                                    %>
                                </select>
                            </div>
                           
                            <input type="hidden" name="id" id="id" value="<%=id%>">
                          
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