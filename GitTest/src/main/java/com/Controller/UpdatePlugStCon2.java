package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AreaDAO;
import com.DAO.PlugDAO;
import com.VO.AreaVO;
import com.VO.PlugVO;


@WebServlet("/UpdatePlugStCon2")
public class UpdatePlugStCon2 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		PlugDAO dao = new PlugDAO();
		int cnt = 0;
		
		AreaDAO aDao = new AreaDAO();
		ArrayList<AreaVO> al1 = aDao.getRoom("1");
		ArrayList<AreaVO> al2 = aDao.getRoom("0");
		ArrayList<ArrayList<PlugVO>> al3 = new ArrayList<ArrayList<PlugVO>>();
		ArrayList<ArrayList<PlugVO>> al4 = new ArrayList<ArrayList<PlugVO>>();

		for(int i = 0; i<al1.size(); i++) {
			al3.add(dao.selectList3(al1.get(i).getAreaId()));
		}
		for(int i = 0; i<al2.size(); i++) {
			al4.add(dao.selectList3(al2.get(i).getAreaId()));				
		}
		for(int i = 0; i<al3.size(); i++) {
			for(int j = 0; j<al3.get(i).size(); j++) {				
				cnt = dao.updateStatus(al3.get(i).get(j).getPlugSeq(), "1");
			}
		}
		for(int i = 0; i<al4.size(); i++) {
			for(int j = 0; j<al4.get(i).size(); j++) {				
				cnt = dao.updateStatus(al4.get(i).get(j).getPlugSeq(), "0");
			}
		}
		
		if(cnt>0) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("#");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("main.jsp");
		}
		
	}

}
