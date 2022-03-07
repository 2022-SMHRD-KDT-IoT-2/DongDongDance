package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class RegAreaCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("aid");
		String name = request.getParameter("aname");

		AreaDAO dao = new AreaDAO();
		dao.regArea(id, name);
		
		return "A_crud.jsp";
	}

}
