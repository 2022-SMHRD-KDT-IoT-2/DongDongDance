package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;

public class AreaCheckCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		  String area = request.getParameter("area");
		  System.out.println(area);
			
	      AreaDAO dao = new AreaDAO();
	      boolean check = dao.areaCheck(area);
			    
		  return String.valueOf(check);
	}

}
