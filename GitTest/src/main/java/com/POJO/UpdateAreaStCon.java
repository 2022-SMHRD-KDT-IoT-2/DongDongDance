package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class UpdateAreaStCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		AreaDAO dao = new AreaDAO();
		dao.updateStatus(id, status);
		
		return "#.jsp";
	}

}
