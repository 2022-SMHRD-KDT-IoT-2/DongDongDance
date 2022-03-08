package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.EmployeeVO;


public class EmployeeDAO {

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
	
	public EmployeeVO login(String id, String pw) {
		EmployeeVO vo = null;
		
		try {
			connect();

			String sql = "select * from t_employee where emp_id = ? and emp_pw = ? and admin_yn = 'Y'";
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
			
			    vo = new EmployeeVO(getid, getname, getseat, getstatus, getsuperid, getadminyn, getrfid, getarea);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public boolean idCheck(String id){

		boolean check = false;
		
		try {
			connect();
			
			String sql = "select emp_id from t_employee where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next()){
				check = true;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return check;
	}
	
	public ArrayList<EmployeeVO> selectAll(){

		ArrayList<EmployeeVO> al = new ArrayList<EmployeeVO>();
		
		try {
			connect();
			
			String sql = "select emp_id, emp_name, emp_seat_no, emp_phone, emp_status, substr(emp_joindate, 1, 10), emp_super_id, admin_yn, rfid_uid, area_id, emp_phone from t_employee";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();	
			
			while(rs.next()){
				String getid = rs.getString(1);
				String getname = rs.getString(2);
				String getseat = rs.getString(3);
				String getphone = rs.getString(4);
				String getstatus = rs.getString(5);
				String getdate = rs.getString(6);
				String getsuperid = rs.getString(7);
				String getadminyn = rs.getString(8);
				String getrfid = rs.getString(9);
				String getarea = rs.getString(10);
				
				EmployeeVO vo = new EmployeeVO(getid, getname, getseat, getphone, getstatus, getdate, getsuperid, getadminyn, getrfid, getarea);
				al.add(vo);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;

	}

	public EmployeeVO selectOne(String id) {
		EmployeeVO vo = null;
	
		try {
			connect();
			
			String sql = "select emp_id, emp_name, emp_seat_no, emp_phone, emp_status, substr(emp_joindate, 1, 10), emp_super_id, admin_yn, rfid_uid, area_id from t_employee where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()){
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
				
				if(getstatus.equals("1")) {
					getstatus = "재실";
				}else {
					getstatus = "퇴실";
				}
				
				vo = new EmployeeVO(getid, getname, getseat, getphone, getstatus, getjoindate, getsuperid, getadminyn, getrfid, getarea);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;

	}
		
	
	// 직원 생성 시, 기본 pw는 id와 동일하게 설정(사용자가 직접 pw 수정하게끔)
	// 직원 생성 시, 상태 값은 0(디폴트)으로 설정
	public int regEmp(String id, String name, String seat, String phone, String superid, String yn, String rfid, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "insert into t_employee values (?, ?, ?, ?, ?, '0', sysdate, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, id);
			psmt.setString(3, name);
			psmt.setString(4, seat);
			psmt.setString(5, phone);
			psmt.setString(6, superid);
			psmt.setString(7, yn);
			psmt.setString(8, rfid);
			psmt.setString(9, area);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	

	//관리자용 직원정보수정(구역정보를 수정하지 않는 경우)
	public int updateEmp(String id, String pw, String name, String seat, String phone, String superid, String yn, String rfid, String area) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_employee set emp_pw = ?, emp_name = ?, emp_seat_no = ?, emp_phone = ?, emp_super_id = ?, admin_yn = ?, rfid_uid = ?, area_id = ? where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, name);
			psmt.setString(3, seat);
			psmt.setString(4, phone);
			psmt.setString(5, superid);
			psmt.setString(6, yn);
			psmt.setString(7, rfid);
			psmt.setString(8, area);
			psmt.setString(9, id);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	
	//직원용 직원정보수정(사용안함)
	public int updateEmp2(String pw1, String pw2, String phone, String id) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_employee set emp_pw = ?, emp_phone = ? where emp_id = ? and emp_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw1);
			psmt.setString(2, phone);
			psmt.setString(3, id);
			psmt.setString(4, pw2);
			
			cnt = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 직원 상태 수정 (emp_id 입력)
	public int updateStatus(String id, String status) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "update t_employee set emp_status = ? where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, status);
			psmt.setString(2, id);

			cnt = psmt.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	
	// 직원 정보 삭제
	public int deleteEmp(String id) {
		int cnt = 0;
		
		try {
			connect();
			
			String sql = "delete from t_employee where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			cnt = psmt.executeUpdate();
					
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
	
	// 구역내 재실 현황 추출
	public ArrayList<EmployeeVO> selectArea(){

		ArrayList<EmployeeVO> al = new ArrayList<EmployeeVO>();
		
		try {
			connect();
			
			String sql = "select area_id from t_employee where emp_status = '1'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()){
			String getarea = rs.getString(1);

				
			EmployeeVO vo = new EmployeeVO(getarea);
			al.add(vo);
			}		
						
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close();
		}
		return al;

	}
	
	// 구역내 퇴실 현황 추출
		public ArrayList<EmployeeVO> selectArea2(){

			ArrayList<EmployeeVO> al = new ArrayList<EmployeeVO>();
			
			try {
				connect();
				
				String sql = "select area_id from t_employee where emp_status = '0' and area_id not in (select area_id from t_employee where emp_status = '1')";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				
				while(rs.next()){
				String getarea = rs.getString(1);

					
				EmployeeVO vo = new EmployeeVO(getarea);
				al.add(vo);
				}		
							
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				close();
			}
			return al;

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
	
	// 직급 표시
    public String selectPo(String id) {
      
      String ts = "최고관리자";
      try {
         connect();
         String sql = "select admin_yn, emp_super_id from t_employee where emp_id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         if (rs.next()) {
            String getyn = rs.getString(1);
            String getsuper = rs.getString(2);
           
            if(getyn.equals("Y") && getsuper.equals("admin")) {
               ts = "일반관리자";
            }else {
               ts = "직원";
            }
         }
         

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return ts;
   }
	
	
}


