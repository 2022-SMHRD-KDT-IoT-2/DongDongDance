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
	
	public double doub(String str) {
		double value1f = Double.parseDouble(str);
		if(value1f == 0.09 || value1f == -0.09) {
			value1f = 0;
		}
		return value1f;
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String value1 = request.getParameter("value1");
		String value2 = request.getParameter("value2");
		String value3 = request.getParameter("value3");
		String value4 = request.getParameter("value4");
		
		System.out.println("value1 :"+ value1);
		System.out.println("value2 :"+ value2);
		System.out.println("value3 :"+ value3);
		System.out.println("value4 :"+ value4);
		System.out.println();
		PlugSenDAO dao = new PlugSenDAO();
		
		if(value1 != null) {
			double value1f = doub(value1);
			double value2f = doub(value2);
			double value3f = doub(value3);
			double value4f = doub(value4);
			
			dao.regPlugSen(3,value1f);
			dao.regPlugSen(45,value2f);
			dao.regPlugSen(47,value3f);
			dao.regPlugSen(49,value4f);
		}
		
	}

}
