package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugSenDAO;

public class RegPlugSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int plug = Integer.parseInt(request.getParameter("plug"));
		double value = Double.parseDouble(request.getParameter("setting"));

	    PlugSenDAO dao = new PlugSenDAO();
		dao.regPlugSen(plug, value);
		
		return "#.jsp";
	}

}
