package elec;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.PlugSenDAO;

@WebServlet("/Elect_read")
public class Elect_read extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		System.out.println(uid);
		PrintWriter out = response.getWriter();
		out.println(uid);
		
		float elect = 12.34f;
		PlugSenDAO dao = new PlugSenDAO();
		dao.regPlugSen(3,elect);
		
	}

}
