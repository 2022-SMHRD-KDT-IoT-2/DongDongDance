package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import com.DAO.RfidDAO;

public class UpdateLogCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String type = request.getParameter("type");
		String memo = request.getParameter("memo");

		RfidDAO dao = new RfidDAO();
		dao.updateLog(seq, type, memo);
		
		return "#.jsp";
	}

}
