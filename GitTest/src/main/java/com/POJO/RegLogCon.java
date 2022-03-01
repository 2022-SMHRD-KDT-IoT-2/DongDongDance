package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.RfidDAO;

public class RegLogCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		RfidDAO dao = new RfidDAO();
		dao.regLog(type, id);
		
		return "#.jsp";
	}

}
