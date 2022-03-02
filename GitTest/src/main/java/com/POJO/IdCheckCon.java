package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;

public class IdCheckCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
		System.out.println(id);
		
        EmployeeDAO dao = new EmployeeDAO();
        boolean check = dao.idCheck(id);
		    
		return String.valueOf(check);
	}

}
