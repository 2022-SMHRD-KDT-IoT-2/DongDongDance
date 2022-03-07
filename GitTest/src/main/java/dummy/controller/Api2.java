package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/Api2")
public class Api2 extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", 45);		
		// JsonObject를 Json 문자열로 변환
		String jsonStr = gson.toJson(jsonObject);
		// 생성된 Json 문자열 출력
		
		response.setStatus(200);
		response.setCharacterEncoding("euc-kr");				
		response.setContentType("application/json");
		response.getWriter().write(jsonStr);
	
	}

}
