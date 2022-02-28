package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class UpdateAreaCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String target_id = request.getParameter("area_u_tid");
		String id = request.getParameter("area_u_id");
		String status = request.getParameter("area_u_status");
		String name = request.getParameter("area_u_name");

		AreaDAO dao = new AreaDAO();
		dao.updateArea(target_id, id, status, name);
		
		return "A_crud.jsp";
	}

}
