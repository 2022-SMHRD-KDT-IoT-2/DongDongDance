package com.FrontController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.POJO.DeleteAreaCon;
import com.POJO.DeleteEmpCon;
import com.POJO.LoginCon;
import com.POJO.LogoutCon;
import com.POJO.RegAreaCon;
import com.POJO.RegEmpCon;
import com.POJO.UpdateEmp1Con;
import com.POJO.UpdateEmp2Con;


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
		}else if(result.equals("LogoutCon.do")) {
			command = new LogoutCon();
		}else if(result.equals("RegEmpCon.do")) {
			command = new RegEmpCon();
		}else if(result.equals("RegAreaCon.do")) {
			command = new RegAreaCon();
		}else if(result.equals("UpdateEmp1Con.do")) {
			command = new UpdateEmp1Con();
		}else if(result.equals("UpdateEmp2Con.do")) {
			command = new UpdateEmp2Con();
		}else if(result.equals("DeleteEmpCon.do")) {
			command = new DeleteEmpCon();
		}else if(result.equals("DeleteAreaCon.do")) {
			command = new DeleteAreaCon();
		}
		
		String url = command.execute(request, response);
		response.sendRedirect(url);
	}

}
