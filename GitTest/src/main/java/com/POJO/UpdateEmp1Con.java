package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;

public class UpdateEmp1Con implements Command {

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
			
		EmployeeDAO dao = new EmployeeDAO();
		dao.updateEmp1(id, name, seat, phone, superid, yn, rfid, area);
	
		return "main2.jsp";
}
}
