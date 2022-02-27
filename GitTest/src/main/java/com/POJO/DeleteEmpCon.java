package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;

public class DeleteEmpCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.deleteEmp(id);

		return "main2.jsp";
}
}
