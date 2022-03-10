package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.DAO.PlugDAO;
import com.VO.EmployeeVO;

public class UpdatePlugCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = request.getParameter("id");
		double power = Double.parseDouble(request.getParameter("power"));
		String device = request.getParameter("device");
		String area = request.getParameter("area");
		
		if(id.equals("null")) {
			id = "";
		}
		if(device.equals("null")) {
			device = "";
		}
		
		EmployeeDAO edao = new EmployeeDAO();
		if(!id.equals("")) {
			EmployeeVO vo = edao.selectOne(id); 
			area = vo.getAreaId();
		}
		
		PlugDAO dao = new PlugDAO();
		dao.updatePlug(seq, id, power, device, area);
		
		return "management.jsp";
	}
	

}
