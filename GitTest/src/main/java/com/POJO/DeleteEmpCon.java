package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.DAO.RfidDAO;

public class DeleteEmpCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
		
        RfidDAO rdao = new RfidDAO();
		EmployeeDAO dao = new EmployeeDAO();
		rdao.deleteId(id);
		dao.deleteEmp(id);

		return "mng_emp.jsp";
}
}
