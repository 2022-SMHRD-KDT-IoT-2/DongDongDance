package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;

public class DeletePlugCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

		PlugDAO dao = new PlugDAO();
		dao.deletePlug(seq);

		return "management.jsp";
	}

}
