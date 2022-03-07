package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.VO.EmployeeVO;

public class RfidDAO {
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

	public int regLog(String type, String id) {
		int cnt = 0;

		try {
			connect();

			String sql = "insert into t_rfid_log values (t_rfid_log_seq.nextval, ?, sysdate, ?, null)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			psmt.setString(2, id);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	public int updateLog(int seq, String type, String memo) {
		int cnt = 0;

		try {
			connect();

			String sql = "update t_rfid_log set log_type = ?, emp_id = ? where log_seq = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			psmt.setString(2, memo);
			psmt.setInt(3, seq);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	public int deleteLog(int seq) {
		int cnt = 0;
		try {
			connect();

			String sql = "delete from t_rfid where log_seq = ?";
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

	public String select_empid(String uid) {

		try {
			connect();
			String sql = "select emp_id from t_employee where rfid_uid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String getid = rs.getString(1);
				return getid;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	public String select_status(String uid) {

		try {
			connect();
			String sql = "select emp_status from t_employee where rfid_uid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String getstatus = rs.getString(1);
				return getstatus;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

}
