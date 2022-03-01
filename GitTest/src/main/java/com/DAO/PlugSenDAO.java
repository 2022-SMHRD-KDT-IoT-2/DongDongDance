package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlugSenDAO {
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
	
	        // 전기센서값 등록
			public int regPlugSen(int plug, double value) {
				int cnt = 0;
				
				try {
					connect();
					
					String sql = "insert into t_plug_sensing values (t_plug_sensing_seq.nextval, ?, ?, sysdate)";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, plug);
					psmt.setDouble(2, value);

					
					cnt = psmt.executeUpdate();
					
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					close();
				}
				return cnt;
			}
			
			// 전기센서값 정보 삭제
			public int deletePlugSen(int seq) {
				int cnt = 0;
				try {
					connect();

					String sql = "delete from t_plug_sensing where ps_seq = ?";
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
