package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;
import com.DAO.PlugSenDAO;

public class DeletePlugCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));

		PlugSenDAO sDao = new PlugSenDAO();
		PlugDAO dao = new PlugDAO();
		sDao.deletePlug(seq);
		dao.deletePlug(seq);

		return "management.jsp";
	}

}
