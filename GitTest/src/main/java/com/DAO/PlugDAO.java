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
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net";
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
	
	public int regPlug(String seat, double power, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "insert into t_plug values (t_plug_seq.nextval, ?, ?, 'D', sysdate, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, seat);
			psmt.setDouble(2, power);
			psmt.setString(3, area);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	public int updatePlug(int seq, String seat, double power, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_plug set seat_num = ?, plug_power = ?, area_id = ? where plug_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, seat);
			psmt.setDouble(2, power);
			psmt.setString(3, area);
			psmt.setInt(4, seq);
			
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
				String getseat = rs.getString(2);
				Double getpower = rs.getDouble(3);
				String getstatus = rs.getString(3);
				String getdate = rs.getString(4);
				String getarea = rs.getString(5);

				
				PlugVO vo = new PlugVO(getplug, getseat, getpower, getstatus, getdate, getarea);
				al.add(vo);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;

	}
	
	public PlugVO selectOne(String seat) {
		PlugVO vo = null;
	
		try {
			connect();
			
			String sql = "select * from t_plug where seat_no = ?";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			psmt.setString(1, seat);
			
			if(rs.next()){
				int getplug = rs.getInt(1);
				String getseat = rs.getString(2);
				Double getpower = rs.getDouble(3);
				String getstatus = rs.getString(3);
				String getdate = rs.getString(4);
				String getarea = rs.getString(5);

				
				vo = new PlugVO(getplug, getseat, getpower, getstatus, getdate, getarea);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;

	}
	
	

}
