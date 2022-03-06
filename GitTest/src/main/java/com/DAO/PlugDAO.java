package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.PlugVO;

public class PlugDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void connect() {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_d_3_0216";
			String dbpw = "smhrd3";			
			conn = DriverManager.getConnection(url, dbid, dbpw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int regPlug(String id, double power, String area, String device) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "insert into t_plug values (t_plug_seq.nextval, ?, ?, 'D', sysdate, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setDouble(2, power);
			psmt.setString(3, area);
			psmt.setString(4, device);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 플러그 정보 수정 (관리자용)
	public int updatePlug(int seq, String id, double power, String area, String device) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_plug set emp_id = ?, plug_power = ?, area_id = ?, plug_device = ? where plug_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setDouble(2, power);
			psmt.setString(3, area);
			psmt.setString(4, device);
			psmt.setInt(5, seq);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 플러그 상태 수정
	public int updateStatus(int seq, String status) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_plug set plug_status = ? where plug_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, status);
			psmt.setInt(2, seq);

			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	//플러그 연결장치 수정 (직원용)
	public int updateDevice(int seq, String device) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_plug set plug_device = ? where plug_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, device);
			psmt.setInt(2, seq);

			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	public int deletePlug(int seq) {
		int cnt = 0;
		try {
			connect();

			String sql = "delete from t_plug where plug_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public ArrayList<PlugVO> selectAll(){

		ArrayList<PlugVO> al = new ArrayList<PlugVO>();
		
		try {
			connect();
			
			String sql = "select * from t_plug";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();	
			
			while(rs.next()){
				int getplug = rs.getInt(1);
				String getid = rs.getString(2);
				Double getpower = rs.getDouble(3);
				String getstatus = rs.getString(4);
				String getdate = rs.getString(5);
				String getarea = rs.getString(6);
				String getdevice = rs.getString(7);

				
				PlugVO vo = new PlugVO(getplug, getid, getpower, getstatus, getdate, getarea, getdevice);
				al.add(vo);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;

	}
	
	// 상태 변경 플러그 번호 추출 (직원번호가 있는 플러그)
	
	public ArrayList<PlugVO> selectList1(String id){

		ArrayList<PlugVO> al = new ArrayList<PlugVO>();
	
		try {
			connect();
			
			String sql = "select * from t_plug where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			psmt.setString(1, id);
			
			while(rs.next()){
				int getplug = rs.getInt(1);
				String getid = rs.getString(2);
				Double getpower = rs.getDouble(3);
				String getstatus = rs.getString(4);
				String getdate = rs.getString(5);
				String getarea = rs.getString(6);
				String getdevice = rs.getString(7);

				
				PlugVO vo = new PlugVO(getplug, getid, getpower, getstatus, getdate, getarea, getdevice);
			    al.add(vo);
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;
	}

	
	
	// 상태 변경 플러그 번호 추출 (직원번호가 없는 플러그)
	
		public ArrayList<PlugVO> selectList2(String status){

			ArrayList<PlugVO> al = new ArrayList<PlugVO>();
		
			try{
				connect();
				
				String sql = "select * from t_plug where emp_id is null and area_id in (select area_id from t_area where status = ?)";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				psmt.setString(1, status);
				
				while(rs.next()){
					int getplug = rs.getInt(1);
					String getid = rs.getString(2);
					Double getpower = rs.getDouble(3);
					String getstatus = rs.getString(4);
					String getdate = rs.getString(5);
					String getarea = rs.getString(6);
					String getdevice = rs.getString(7);

					
					PlugVO vo = new PlugVO(getplug, getid, getpower, getstatus, getdate, getarea, getdevice);
				    al.add(vo);
				}
							
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				close();
			}
			return al;
		}
		
		// 상태 변경 플러그 번호 추출 (방번호가 일치하는 플러그 중에서 직원번호가 없는 플러그)
		
			public PlugVO selectList3(String room){

				PlugVO vo = null;
			
				try{
					connect();
					
					String sql = "select plug_seq from t_plug where emp_id is null and area_id in (select area_id from t_area where area_id like '?%')";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					psmt.setString(1, room);
					
					while(rs.next()){
						int getplug = rs.getInt(1);
//						String getid = rs.getString(2);
//						Double getpower = rs.getDouble(3);
//						String getstatus = rs.getString(4);
//						String getdate = rs.getString(5);
//						String getarea = rs.getString(6);
//						String getdevice = rs.getString(7);
//
//						
						vo = new PlugVO(getplug);
//					    al.add(getplug);
					}
								
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					close();
				}
				return vo;
			}
			
			// 상태변경 플러그 번호 추출
			public ArrayList<PlugVO> selectStatus(String status){

				ArrayList<PlugVO> al = new ArrayList<PlugVO>();
			
				try{
					connect();
					
					String sql = "select plug_seq from t_plug where status = ?";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					psmt.setString(1, status);
					
					while(rs.next()){
						int getplug = rs.getInt(1);
						String getid = rs.getString(2);
						Double getpower = rs.getDouble(3);
						String getstatus = rs.getString(4);
						String getdate = rs.getString(5);
						String getarea = rs.getString(6);
						String getdevice = rs.getString(7);

						
						PlugVO vo = new PlugVO(getplug, getid, getpower, getstatus, getdate, getarea, getdevice);
					    al.add(vo);
					}
								
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					close();
				}
				return al;
			}


}
