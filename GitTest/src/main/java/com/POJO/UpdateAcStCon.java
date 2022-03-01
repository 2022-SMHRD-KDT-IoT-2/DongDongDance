package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AirconditionerDAO;

public class UpdateAcStCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String status = request.getParameter("status");
		
		AirconditionerDAO dao = new AirconditionerDAO();
		dao.updateStatus(seq, status);
		
		return "#.jsp";
	}

}
