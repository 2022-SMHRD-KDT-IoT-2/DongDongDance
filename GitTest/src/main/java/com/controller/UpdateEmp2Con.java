package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.EmployeeDAO;
import com.model.EmployeeVO;


@WebServlet("/UpdateEmp2Con")
public class UpdateEmp2Con extends HttpServlet {

	// 직원용 직원정보 수정
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		EmployeeVO vo = (EmployeeVO)session.getAttribute("loginvo");
		String name = vo.getEmpName();
		String seat = vo.getEmpSeatNo();
		String status = vo.getEmpStatus();
		String superid = vo.getEmpSuperId();
		String yn = vo.getAdminYn();
		String rfid = vo.getRfidUid();
		String area = vo.getAreaId();
		int plug = vo.getPlugSeq();
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.updateEmp2(id, pw, name);

			if(cnt>0) {
				response.sendRedirect("main1.jsp");
				System.out.println("수정성공");
				vo = new EmployeeVO(id, name, seat, status, superid, yn, rfid, area, plug);
				session.setAttribute("loginvo", vo);
			}else {
				response.sendRedirect("main1.jsp");
				System.out.println("수정실패");
			}
	}

}
