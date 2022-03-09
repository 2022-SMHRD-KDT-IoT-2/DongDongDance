package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class UpdateAreaCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String target_id = request.getParameter("tid");
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		System.out.println(id);
		AreaDAO dao = new AreaDAO();
		dao.updateArea(target_id, id, name);
		
		return "management.jsp";
	}

}
