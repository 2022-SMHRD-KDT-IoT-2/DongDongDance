package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;

public class LoginCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String url = "";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(id, pw);
		
		if(vo != null) {
		HttpSession session = request.getSession();
		session.setAttribute("loginvo", vo);
		if(vo.getAdminYn().equals("Y")) {			
			url = "main.jsp";
		}else {
			url = "Login.jsp";
		}
	}
		return url;

}
}
