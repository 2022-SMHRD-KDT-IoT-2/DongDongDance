package rfid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.RfidDAO;

@WebServlet("/Rfid_read")
public class Rfid_read extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uid = request.getParameter("uid");
//		System.out.println(uid);
//		PrintWriter out = response.getWriter();
//		out.println(uid);
		
		String uid = "1234";
		
		RfidDAO dao = new RfidDAO();
		
		String uid2 = dao.select_empid(uid);
		dao.regLog("1", uid2);
	}

}
