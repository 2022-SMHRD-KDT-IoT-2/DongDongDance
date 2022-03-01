package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.PlugVO;

public class LightDAO {
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
	
	// 조명등록
	public int regLight(String loc, int setting, String status, String id, int plug, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "insert into t_light values (t_light_seq.nextval, ?, ?, 'D', sysdate, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loc);
			psmt.setInt(2, setting);
			psmt.setString(3, status);
			psmt.setString(4, id);
			psmt.setInt(5, plug);
			psmt.setString(6, area);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 조명 정보 수정 (상태 값 제외)
	public int updateLight(int seq, String loc, int setting, String id, int plug, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_light set light_loc = ?, light_setting = ?, emp_id = ?, plug_seq = ?, area_id = ? where light_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loc);
			psmt.setInt(2, setting);
			psmt.setString(3, id);
			psmt.setInt(4, plug);
			psmt.setString(5, area);
			psmt.setInt(6, seq);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 조명 상태 수정
	public int updateStatus(int seq, String status) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_light set light_status = ? where light_seq = ?";
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
	
	// 조명 정보 삭제
	public int deleteLight(int seq) {
		int cnt = 0;
		try {
			connect();

			String sql = "delete from t_light where light_seq = ?";
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
	
}
