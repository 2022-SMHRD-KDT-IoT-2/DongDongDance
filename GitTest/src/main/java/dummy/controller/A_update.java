package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.AreaDAO;
import com.VO.AreaVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/A_update")
public class A_update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		AreaDAO dao = new AreaDAO();

		int cnt = 0;
		HttpSession session = request.getSession();
		AreaVO vo = (AreaVO) session.getAttribute("#");
		String target_id = request.getParameter("area_u_tid");
		String id = request.getParameter("area_u_id");
		String name = request.getParameter("area_u_name");

		cnt = dao.updateArea(target_id, id, name);
		if (cnt > 0) {
			System.out.println("업데이트 성공");
			response.sendRedirect("A_crud.jsp");
//			response.sendRedirect("#");
		} else {
			System.out.println("업데이트 실패");
//			response.sendRedirect("#");
		}
//		if (cnt > 0) {
//			response.sendRedirect("#");
//			vo = new AreaVO(id, status, name);
//			session.setAttribute("areavo", vo); // 세션이름 어떻게??
//		} else {
//			response.sendRedirect("#");
//		}

	}
}
