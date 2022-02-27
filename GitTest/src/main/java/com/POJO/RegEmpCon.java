package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;

public class RegEmpCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String seat = request.getParameter("seat");
		String phone = request.getParameter("phone");
		String superid = request.getParameter("superid");
		String yn = request.getParameter("yn");
		String rfid = request.getParameter("rfid");
		String area = request.getParameter("area");
		int plug = Integer.parseInt(request.getParameter("plug"));
		
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.regEmp(id, name, seat, phone, superid, yn, rfid, area, plug);
		
		return "main2.jsp";
}
}
