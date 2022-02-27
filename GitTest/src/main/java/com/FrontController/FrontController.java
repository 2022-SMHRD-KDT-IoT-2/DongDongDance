package com.FrontController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.POJO.LoginCon;


// 확장자패턴 사용 : 어떤 요청이던 뒤에 .do가 붙어 있으면 이 서블릿에서 받아서 처리
@WebServlet("*.do")
public class FrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		// 요청 URI
		String reqURI = request.getRequestURI();
		
		// 프로젝트 명
		String path = request.getContextPath();
		
		// 문자열 자르기 -> reqURI - 프로젝트명/
		String result = reqURI.substring(path.length()+1);
		
		Command command = null;
		
		if(result.equals("LoginCon.do")) {
			command = new LoginCon();
		}else if(result.equals("JoinCon.do")) {
			
		}else if(result.equals("UpdateCon.do")) {
			
		}else if(result.equals("DeleteCon.do")) {

		}else if(result.equals("LogoutCon.do")) {
			
		}else if(result.equals("IdCheckCon.do")) {
			
		}
		
		String url = command.execute(request, response);
		response.sendRedirect(url);
	}

}
