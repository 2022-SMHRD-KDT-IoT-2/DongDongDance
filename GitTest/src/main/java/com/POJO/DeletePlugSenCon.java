package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugSenDAO;

public class DeletePlugSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

	    PlugSenDAO dao = new PlugSenDAO();
		dao.deletePlugSen(seq);
		
		return "#.jsp";
	}

}
