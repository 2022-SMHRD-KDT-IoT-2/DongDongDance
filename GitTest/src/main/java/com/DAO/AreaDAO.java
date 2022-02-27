package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.AreaVO;

public class AreaDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// LoginCon -> login()
	// JoinCon -> join()
	// UpdateCon -> update()
	public void conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "campus_d_3_0216";
			String dbpw = "smhrd3";
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//			String dbid = "hr";
//			String dbpw = "hr";

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
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public int join(String id, String name) {

		int cnt = 0;
		try {
			conn();

			// 3. SQL준비
			String sql = "insert into T_AREA values (?,'N',?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, name);

			// 4. SQL실행
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public int update(String target_id, String id, String status, String name) { // 일단 인자 다넣음 // 추후 수정

		int cnt = 0;
		try {
			conn();

			String sql = "update T_AREA set AREA_ID = ?, AREA_STATUS = ?, AREA_NAME = ? where AREA_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, status);
			psmt.setString(3, name);
			psmt.setString(4, target_id);

			// 4. SQL실행
			cnt = psmt.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public ArrayList<AreaVO> getList() {

		ArrayList<AreaVO> al = new ArrayList<>();
		try {
			conn();

			String sql = "select * from T_AREA";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				String id = rs.getString(1);
				String status = rs.getString(2);
				String name = rs.getString(3);

				AreaVO vo = new AreaVO(id, status, name);
				al.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return al;
	}

	public int delete(String target_id) {
		int cnt = 0;
		try {
			conn();

			// 3. SQL준비
			String sql = "delete from T_AREA where AREA_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, target_id);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	
}
