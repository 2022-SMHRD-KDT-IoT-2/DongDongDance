package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.RfidDAO;

public class DeleteLogCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		RfidDAO dao = new RfidDAO();
		dao.deleteLog(seq);
		
		return "log_read.jsp";
	}

}
