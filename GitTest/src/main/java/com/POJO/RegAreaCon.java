package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class RegAreaCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("area_i_id");
		String name = request.getParameter("area_i_name");

		AreaDAO dao = new AreaDAO();
		dao.regArea(id, name);
		
		return "A_crud.jsp";
	}

}
