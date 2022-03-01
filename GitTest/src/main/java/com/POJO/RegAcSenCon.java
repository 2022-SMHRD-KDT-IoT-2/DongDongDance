package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AirconditionerSenDAO;

public class RegAcSenCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int plug = Integer.parseInt(request.getParameter("plug"));
		double value = Double.parseDouble(request.getParameter("setting"));

	    AirconditionerSenDAO dao = new AirconditionerSenDAO();
		dao.regAcSen(plug, value);
		
		return "#.jsp";
	}

}
