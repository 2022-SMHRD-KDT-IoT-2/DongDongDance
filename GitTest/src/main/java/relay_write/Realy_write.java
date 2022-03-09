package relay_write;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.PlugDAO;

@WebServlet("/Realy_write")
public class Realy_write extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PlugDAO pDao = new PlugDAO();
		String str = pDao.select_state();
	
		char charAt1 = str.charAt(0);
		char charAt2 = str.charAt(1);
		char charAt3 = str.charAt(2);
		char charAt4 = str.charAt(3);
		
		System.out.println(charAt1);
		System.out.println(charAt2);
		System.out.println(charAt3);
		System.out.println(charAt4);
		
		String result = "{\"relay1\" : \""+charAt1+"\" , \"relay2\" : \""+charAt2+"\" , \"relay3\" : \""+charAt3+"\" , \"relay4\" : \""+charAt4+"\"}";
		out.print(result);
		
		
	}

}
