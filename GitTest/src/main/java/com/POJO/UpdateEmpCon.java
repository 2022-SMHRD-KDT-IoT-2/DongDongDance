package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;

public class UpdateEmpCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String seat = request.getParameter("seat");
		String phone = request.getParameter("phone");
		String superid = request.getParameter("superid");
		String yn = request.getParameter("yn");
		String rfid = request.getParameter("rfid");
		
		HttpSession session = request.getSession();
		EmployeeVO vo = (EmployeeVO)session.getAttribute("loginvo");
		String id2 = vo.getEmpId();
		String status = vo.getEmpStatus();
		String area = vo.getAreaId();
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = 0;
		
		if((id.equals(id2) || yn.equals("N")) || superid.equals("")) {
			if((id.equals(id2)) && superid.equals("")) {
				yn = "Y";
			}
			cnt = dao.updateEmp(id, pw, name, seat, phone, superid, yn, rfid);
		}
		
		if((cnt > 0) && (id.equals(id2))) {
			vo = new EmployeeVO(id, name, seat, status, superid, yn, rfid, area);
			session.setAttribute("loginvo", vo);
		}
	
		return "main2.jsp";
}
}
