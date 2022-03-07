package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class DeleteAreaCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("aid");

		AreaDAO dao = new AreaDAO();
		dao.deleteArea(id);

		return "main2.jsp";
	}
	

}
