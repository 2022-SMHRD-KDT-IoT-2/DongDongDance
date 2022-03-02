package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;

public class UpdatePlugDvCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String device = request.getParameter("device");

		PlugDAO dao = new PlugDAO();
		dao.updateDevice(seq, device);
		
		return "main2.jsp";
	}

}
