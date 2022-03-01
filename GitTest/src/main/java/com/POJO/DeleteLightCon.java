package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.LightDAO;

public class DeleteLightCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

		LightDAO dao = new LightDAO();
		dao.deleteLight(seq);

		return "main2.jsp";
	}

}
