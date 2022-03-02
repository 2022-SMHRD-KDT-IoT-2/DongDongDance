package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Command.Command;
import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;

public class UpdateEmp2Con implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String pw1 = request.getParameter("pw1"); // 현재 비밀번호
		String pw2 = request.getParameter("pw2"); // 수정 비밀번호
		String phone = request.getParameter("pw2"); // 수정 핸드폰 번호
		
		HttpSession session = request.getSession();
		EmployeeVO vo = (EmployeeVO)session.getAttribute("loginvo");
		String id = vo.getEmpId();
		String name = vo.getEmpName();
		String seat = vo.getEmpSeatNo();
		String status = vo.getEmpStatus();
		String superid = vo.getEmpSuperId();
		String yn = vo.getAdminYn();
		String rfid = vo.getRfidUid();
		String area = vo.getAreaId();
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.updateEmp2(pw1, pw2, phone, id);

			if(cnt>0) {
				vo = new EmployeeVO(id, name, seat, status, superid, yn, rfid, area);
				session.setAttribute("loginvo", vo);
			}
			
		return "main1.jsp";
}
}
