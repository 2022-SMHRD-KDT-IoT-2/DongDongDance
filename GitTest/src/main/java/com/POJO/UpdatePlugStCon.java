package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;

public class UpdatePlugStCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int plug = Integer.parseInt(request.getParameter("plug"));
		String status = request.getParameter("status");
		
		PlugDAO dao = new PlugDAO();
		dao.updateStatus(plug, status);
		
		return "#.jsp";
	}

}
