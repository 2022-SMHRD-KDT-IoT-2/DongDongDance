package com.POJO;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;
import com.DAO.PlugDAO;
import com.VO.AreaVO;
import com.VO.PlugVO;

public class UpdatePlugFxCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int plug = Integer.parseInt(request.getParameter("plug"));
		String fixed = request.getParameter("fixed");
		String fs = "";
		
		PlugDAO dao = new PlugDAO();
		PlugVO vo = null;
		AreaDAO aDao = new AreaDAO();
		AreaVO avo = null;
		
		if(fixed.equals("0")) {
			fixed = "1";
			fs = "1";
		}else if(fixed.equals("1")){
			fixed = "2";
			fs = "0";
		}else if(fixed.equals("2")){
			fixed = "0";
			vo = dao.selectSeq(plug);
			avo = aDao.getOne(vo.getAreaId());
			fs = avo.getAreaStatus();
		}
		
		dao.updateFixed(plug, fixed);
		dao.updateStatus(plug, fs);
		
		
		return "management.jsp";
	}

}
