package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;

public class DeleteEmpCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
		
		EmployeeDAO dao = new EmployeeDAO();
		dao.deleteEmp(id);

		return "mng_emp.jsp";
}
}
