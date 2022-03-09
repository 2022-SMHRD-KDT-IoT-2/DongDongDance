package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.VO.EmployeeVO;
import com.VO.RfidVO;

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

			String sql = "insert into t_rfid_log values (t_rfid_log_seq.nextval, ?, sysdate + 9/24, ?, null)";
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

			String sql = "update t_rfid_log set log_type = ?, log_memo = ? where log_seq = ?";
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

			String sql = "delete from t_rfid_log where log_seq = ?";
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
	
	// 특정 id의 log기록 전부 삭제
	public int deleteId(String id) {
		int cnt = 0;
		try {
			connect();

			String sql = "delete from t_rfid_log where emp_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

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
	
	public String selectType(String id) {
	      
	      String ts = "";
	      try {
	         connect();
	         String sql = "select log_type from t_rfid_log where emp_id = ? order by log_time desc";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, id);
	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String gettype = rs.getString(1);
	            
	            if(gettype.equals("0")) {
	               ts = "퇴근";
	            }else if(gettype.equals("1")) {
	               ts = "출근";
	            }else if(gettype.equals("2")) {
	               ts = "지각";
	            }else if(gettype.equals("3")) {
	               ts = "조퇴";
	            }else if(gettype.equals("4")) {
	               ts = "주말출근";
	            }else if(gettype.equals("5")) {
	               ts = "주말퇴근";
	            }else if(gettype.equals("6")) {
	               ts = "출근취소";
	            }else if(gettype.equals("7")) {
	               ts = "결근";
	            }else if(gettype.equals("8")) {
	               ts = "점심";
	            }else if(gettype.equals("9")) {
	               ts = "복귀";
	            }else if(gettype.equals("A")) {
	               ts = "추가근무";
	            }
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return ts;
	   }
	   
	    public String selectDate(String id) {
	      
	      String ts = "";
	      try {
	         connect();
	         String sql = "select log_time from t_rfid_log where emp_id = ? order by log_time desc";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, id);
	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String gettime = rs.getString(1);
	            
	            ts = gettime;
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return ts;
	   }
	    
	    public ArrayList<RfidVO> selectAll(String id){

			ArrayList<RfidVO> al = new ArrayList<RfidVO>();
			
			try {
				connect();
				
				String sql = "select log_seq, log_type, log_time, substr(log_memo, 1, 5) from t_rfid_log where emp_id = ? order by log_seq desc";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs = psmt.executeQuery();
				
				while(rs.next()){
					int getseq = rs.getInt(1);
					String gettype = rs.getString(2);
					String gettime = rs.getString(3);
					String getmemo = rs.getString(4);

					String ts = "";
					
					if(gettype.equals("0")) {
			               ts = "퇴근";
			            }else if(gettype.equals("1")) {
			               ts = "출근";
			            }else if(gettype.equals("2")) {
			               ts = "지각";
			            }else if(gettype.equals("3")) {
			               ts = "조퇴";
			            }else if(gettype.equals("4")) {
			               ts = "주말출근";
			            }else if(gettype.equals("5")) {
			               ts = "주말퇴근";
			            }else if(gettype.equals("6")) {
			               ts = "출근취소";
			            }else if(gettype.equals("7")) {
			               ts = "결근";
			            }else if(gettype.equals("8")) {
			               ts = "점심";
			            }else if(gettype.equals("9")) {
			               ts = "복귀";
			            }else if(gettype.equals("A")) {
			               ts = "추가근무";
			            }
					
					RfidVO vo = new RfidVO(getseq, ts, gettime, getmemo);
					al.add(vo);
				}		
							
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				close();
			}
			return al;

		}
	    
	    public RfidVO selectLog(int seq){

			RfidVO vo = null;
			
			try {
				connect();
				
				String sql = "select log_type, log_memo from t_rfid_log where log_seq = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				rs = psmt.executeQuery();
				
				while(rs.next()){
					String gettype = rs.getString(1);
					String getmemo = rs.getString(2);

					String ts = "";
					
					if(gettype.equals("0")) {
			               ts = "퇴근";
			            }else if(gettype.equals("1")) {
			               ts = "출근";
			            }else if(gettype.equals("2")) {
			               ts = "지각";
			            }else if(gettype.equals("3")) {
			               ts = "조퇴";
			            }else if(gettype.equals("4")) {
			               ts = "주말출근";
			            }else if(gettype.equals("5")) {
			               ts = "주말퇴근";
			            }else if(gettype.equals("6")) {
			               ts = "출근취소";
			            }else if(gettype.equals("7")) {
			               ts = "결근";
			            }else if(gettype.equals("8")) {
			               ts = "점심";
			            }else if(gettype.equals("9")) {
			               ts = "복귀";
			            }else if(gettype.equals("A")) {
			               ts = "추가근무";
			            }
					
					vo = new RfidVO(ts, getmemo);
					
				}		
							
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				close();
			}
			return vo;

		}

}
