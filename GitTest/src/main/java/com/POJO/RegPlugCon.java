package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.DAO.PlugDAO;
import com.VO.EmployeeVO;

public class RegPlugCon implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		double power = Double.parseDouble(request.getParameter("power"));
		String area = request.getParameter("area");
		String device = request.getParameter("device");

		EmployeeDAO edao = new EmployeeDAO();
		if(!id.equals("")) {
			EmployeeVO vo = edao.selectOne(id); 
			area = vo.getAreaId();
		}
		
		PlugDAO dao = new PlugDAO();
		dao.regPlug(id, power, area, device);
		
		return "management.jsp";
		
	}

}
