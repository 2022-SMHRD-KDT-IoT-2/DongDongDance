package com.POJO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AreaDAO;
import com.DAO.EmployeeDAO;
import com.DAO.PlugDAO;
import com.VO.AreaVO;
import com.VO.PlugVO;

public class SyncCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		SimpleDateFormat fourteen_format = new SimpleDateFormat("HHmm");  // 포맷
		int time =  Integer.parseInt(fourteen_format.format(date_now));
		
		PlugDAO pDao = new PlugDAO();
		AreaDAO aDao = new AreaDAO();
		boolean check = true;
		// 방별 제어(기본 업무시간)
		if(time > 0600 && time <= 2000) {
				ArrayList<AreaVO> al5 = aDao.getRoom("1");
				ArrayList<AreaVO> al6 = aDao.getRoom("0");
				ArrayList<ArrayList<PlugVO>> al7 = new ArrayList<ArrayList<PlugVO>>();
				ArrayList<ArrayList<PlugVO>> al8 = new ArrayList<ArrayList<PlugVO>>();
					
					
				for(int i = 0; i<al5.size(); i++) {
						al7.add(pDao.selectList3(al5.get(i).getAreaId()));
					}
				for(int i = 0; i<al6.size(); i++) {
						al8.add(pDao.selectList3(al6.get(i).getAreaId()));				
					}
				for(int i = 0; i<al8.size(); i++) {
					for(int j = 0; j<al8.get(i).size(); j++) {				
							pDao.updateStatus(al8.get(i).get(j).getPlugSeq(), "0");
							System.out.println("al8:"+al8.get(i).get(j).getPlugSeq());
						}
					}
				for(int i = 0; i<al7.size(); i++) {
						for(int j = 0; j<al7.get(i).size(); j++) {				
							pDao.updateStatus(al7.get(i).get(j).getPlugSeq(), "1");
							System.out.println("al7:"+al7.get(i).get(j).getPlugSeq());
						}
					}
					
				}else {
					// 구역별제어 (예외시간대)
					ArrayList<AreaVO> al13 = aDao.getArea("1");
					ArrayList<AreaVO> al14 = aDao.getArea("0");
					ArrayList<ArrayList<PlugVO>> al15 = new ArrayList<ArrayList<PlugVO>>();
					ArrayList<ArrayList<PlugVO>> al16 = new ArrayList<ArrayList<PlugVO>>();
					
					
					for(int i = 0; i<al13.size(); i++) {
						al15.add(pDao.selectList3(al13.get(i).getAreaId()));
					}
					for(int i = 0; i<al14.size(); i++) {
						al16.add(pDao.selectList3(al14.get(i).getAreaId()));				
					}
					for(int i = 0; i<al15.size(); i++) {
						for(int j = 0; j<al15.get(i).size(); j++) {				
							pDao.updateStatus(al15.get(i).get(j).getPlugSeq(), "1");
							System.out.println("al15:"+al15.get(i).get(j).getPlugSeq());
						}
					}
					for(int i = 0; i<al16.size(); i++) {
						for(int j = 0; j<al16.get(i).size(); j++) {				
							pDao.updateStatus(al16.get(i).get(j).getPlugSeq(), "0");
							System.out.println("al16:"+al16.get(i).get(j).getPlugSeq());
						}
					}
				}
				
				
				
				
				ArrayList<PlugVO> al9 = pDao.selectFixed("1");
				ArrayList<PlugVO> al10 = pDao.selectFixed("2");
				for(int i = 0; i<al9.size(); i++) {
					pDao.updateStatus(al9.get(i).getPlugSeq(), "1");
				}
				for(int i = 0; i<al10.size(); i++) {
					pDao.updateStatus(al10.get(i).getPlugSeq(), "0");
				}
				
				ArrayList<PlugVO> al11 = pDao.selectStatus("1");
				ArrayList<PlugVO> al12 = pDao.selectStatus("0");
				
				for(int i = 0; i<al11.size(); i++) {
					System.out.println("al11:"+al11.get(i).getPlugSeq());
				}
				for(int i = 0; i<al12.size(); i++) {
					System.out.println("al12:"+al12.get(i).getPlugSeq());
				}
		return String.valueOf(check);
	}

}
