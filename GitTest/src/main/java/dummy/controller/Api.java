package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.EmployeeDAO;
import com.DAO.PlugSenDAO;
import com.VO.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/Api")
public class Api extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		PlugSenDAO dao = new PlugSenDAO();
		jsonObject.addProperty("min", 1);
		jsonObject.addProperty("value", dao.selectall());		
		// JsonObject를 Json 문자열로 변환
		String jsonStr = gson.toJson(jsonObject);
		// 생성된 Json 문자열 출력
		
		response.setStatus(200);
		response.setCharacterEncoding("euc-kr");				
		response.setContentType("application/json");
		response.getWriter().write(jsonStr);
	}

}
