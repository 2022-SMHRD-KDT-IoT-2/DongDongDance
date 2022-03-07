package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;

import com.DAO.PlugDAO;

public class UpdatePlugCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = request.getParameter("id");
		double power = Double.parseDouble(request.getParameter("power"));
		String device = request.getParameter("device");

		PlugDAO dao = new PlugDAO();
		dao.updatePlug(seq, id, power, device);
		
		return "main.jsp";
	}
	

}
