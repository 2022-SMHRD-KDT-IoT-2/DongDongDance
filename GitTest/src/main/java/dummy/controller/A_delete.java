package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AreaDAO;


@WebServlet("/A_delete")
public class A_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("area_d_id");

		AreaDAO dao = new AreaDAO();
		int cnt = dao.deleteArea(id);

		if (cnt > 0) {
			System.out.println("삭제성공");
			response.sendRedirect("A_crud.jsp");
//			response.sendRedirect("#");
		} else {
			System.out.println("삭제실패");
//			response.sendRedirect("#");
		}

	}

}
