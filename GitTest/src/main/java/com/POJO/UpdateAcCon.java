package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AirconditionerDAO;

public class UpdateAcCon implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String loc = request.getParameter("loc");
		double setting = Double.parseDouble(request.getParameter("setting"));
		String id = request.getParameter("id");
		int plug = Integer.parseInt(request.getParameter("plug"));
		String area = request.getParameter("area");

		AirconditionerDAO dao = new AirconditionerDAO();
		dao.updateAc(seq, loc, setting, id, plug, area);
		
		return "main2.jsp";
	}

}
