package dummy.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String seq = request.getParameter("seq"); // 플러그번호 전달받음
		System.out.println("seq : "+seq);
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		SimpleDateFormat fourteen_format = new SimpleDateFormat("HHmmss");  // 포맷
		int time =  Integer.parseInt(fourteen_format.format(date_now));
//		System.out.println(time); // 시간, 분만 나오게함.
		String a = "" + time;
		String b = a.substring(0,2);
		String c = a.substring(2,4);
		String d = a.substring(4,6);
		String e = b+ ":" + c + ":"+d;
		
		if(seq.equals("-1") ) {
			Gson gson = new Gson();
			JsonObject jsonObject = new JsonObject();
			PlugSenDAO dao = new PlugSenDAO();
			jsonObject.addProperty("min", e);
			jsonObject.addProperty("value", dao.selectall());
			System.out.println("-1일때 if문에 들어왓다.");
//			System.out.println("최근 전력값 : "+ dao.selectone(seq1));
			// JsonObject를 Json 문자열로 변환
			String jsonStr = gson.toJson(jsonObject);
			// 생성된 Json 문자열 출력
			
			response.setStatus(200);
			response.setCharacterEncoding("euc-kr");				
			response.setContentType("application/json");
			response.getWriter().write(jsonStr);
		}
		else {
			int seq1 = Integer.parseInt(seq);
			System.out.println("전달받은 seq : "+ seq1);
			Gson gson = new Gson();
			JsonObject jsonObject = new JsonObject();
			PlugSenDAO dao = new PlugSenDAO();
			jsonObject.addProperty("min", e);
			jsonObject.addProperty("value", dao.selectone(seq1));	
			System.out.println("최근 전력값 : "+ dao.selectone(seq1));
			// JsonObject를 Json 문자열로 변환
			String jsonStr = gson.toJson(jsonObject);
			// 생성된 Json 문자열 출력
			
			response.setStatus(200);
			response.setCharacterEncoding("euc-kr");				
			response.setContentType("application/json");
			response.getWriter().write(jsonStr);
		}
		
	}

}
