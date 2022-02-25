package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class EmployeeDAO {

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
	
	public EmployeeVO login(String id, String pw) {
		EmployeeVO vo = null;
		
		try {
			connect();

			String sql = "select * from t_employee where emp_id = ? and emp_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				String getid = rs.getString(1);
				String getpw = rs.getString(2);
				String getname = rs.getString(3);
				String getseat = rs.getString(4);
				String getphone = rs.getString(5);
				String getstatus = rs.getString(6);
				String getjoindate = rs.getString(7);
				String getsuperid = rs.getString(8);
				String getadminyn = rs.getString(9);
				String getrfid = rs.getString(10);
				String getarea = rs.getString(11);
				int getplug = rs.getInt(12);
			
			    vo = new EmployeeVO(getid, getname, getseat, getstatus, getsuperid, getadminyn, getrfid, getarea, getplug);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public ArrayList<EmployeeVO> selectAll(){

		ArrayList<EmployeeVO> al = new ArrayList<EmployeeVO>();
		
		try {
			connect();
			
			String sql = "select emp_id, emp_name, emp_seat_no, emp_phone, emp_status, emp_joindate, emp_super_id, admin_yn, rfid_uid, area_id, plug_seq from t_employee";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();	
			
			while(rs.next()){
				String getid = rs.getString(1);
				String getname = rs.getString(2);
				String getseat = rs.getString(3);
				String getphone = rs.getString(4);
				String getstatus = rs.getString(5);
				String getjoindate = rs.getString(6);
				String getsuperid = rs.getString(7);
				String getadminyn = rs.getString(8);
				String getrfid = rs.getString(9);
				String getarea = rs.getString(10);
				int getplug = rs.getInt(11);
				
				EmployeeVO vo = new EmployeeVO(getid, getname, getseat, getphone, getstatus, getjoindate, getsuperid, getadminyn, getrfid, getarea, getplug);
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
