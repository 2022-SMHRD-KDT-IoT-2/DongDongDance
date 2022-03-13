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

	// 전기센서값 등록
	public int regPlugSen(int plug, double value) {
		int cnt = 0;

		try {
			connect();

			String sql = "insert into t_plug_sensing values (t_plug_sensing_seq.nextval, ?, ?, sysdate + 9/24)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, plug);
			psmt.setDouble(2, value);

			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	// 해당 플러그 기록 전부 삭제
		public int deletePlug(int plug) {
			int cnt = 0;
			try {
				connect();

				String sql = "delete from t_plug_sensing where plug_seq = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, plug);

				cnt = psmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return cnt;
		}

	public double selectall() { // 일단 전체값 중 최근 갱신된 1행의 ps_value 반환
		double ps_value = 0;
		try {
			connect();

			String sql = "SELECT ps_value FROM (SELECT * FROM t_plug_sensing ORDER BY ps_seq desc) WHERE ROWNUM= 1";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				ps_value = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return ps_value*220;
	}

	public double selectone(int seq) { // 플러그 번호대상으로 반환
		double ps_value = 0;
		try {
			connect();
			String sql = "SELECT ps_value FROM (SELECT *FROM t_plug_sensing WHERE PLUG_SEQ = ?  ORDER BY ps_seq desc) WHERE ROWNUM= 1";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();

			if (rs.next()) {
				ps_value = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return ps_value*220;
	}
	
	// 이번달 플러그 사용량 총합
	public double monthlyPower1() {
		double ps_value = 0;
		try {
			connect();

			String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime > sysdate - 30";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				ps_value = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return (ps_value*220)/1000;
	}
	
	// 지난달 플러그 사용량 총합
		public double monthlyPower2() {
			double ps_value = 0;
			try {
				connect();

				String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime between sysdate - 60 and sysdate - 30";

				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();

				if (rs.next()) {
					ps_value = rs.getDouble(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return (ps_value*220)/1000;
		}
		
		// 2달전 플러그 사용량 총합
				public double monthlyPower3() {
					double ps_value = 0;
					try {
						connect();

						String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime between sysdate - 90 and sysdate - 60";

						psmt = conn.prepareStatement(sql);
						rs = psmt.executeQuery();

						if (rs.next()) {
							ps_value = rs.getDouble(1);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						close();
					}
					return (ps_value*220)/1000;
				}
				
				// 이번달 구역별 플러그 사용량 총합
				public double monthlyPowerA1(String aid) {
					double ps_value = 0;
					try {
						connect();

						String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime > sysdate - 30 and plug_seq in (select plug_seq from t_plug where area_id = ?)";

						psmt = conn.prepareStatement(sql);
						psmt.setString(1, aid);
						rs = psmt.executeQuery();

						if (rs.next()) {
							ps_value = rs.getDouble(1);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						close();
					}
					return (ps_value*220)/1000;
				}
				
				// 지난달 구역별 플러그 사용량 총합
					public double monthlyPowerA2(String aid) {
						double ps_value = 0;
						try {
							connect();

							String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime between sysdate - 60 and sysdate - 30 and plug_seq in (select plug_seq from t_plug where area_id = ?)";

							psmt = conn.prepareStatement(sql);
							psmt.setString(1, aid);
							rs = psmt.executeQuery();

							if (rs.next()) {
								ps_value = rs.getDouble(1);
							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							close();
						}
						return (ps_value*220)/1000;
					}
					
					// 이번달 직원별 플러그 사용량 총합
					public double monthlyPowerE1(String id) {
						double ps_value = 0;
						try {
							connect();

							String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime > sysdate - 30 and plug_seq in (select plug_seq from t_plug where emp_id = ?)";

							psmt = conn.prepareStatement(sql);
							psmt.setString(1, id);
							rs = psmt.executeQuery();

							if (rs.next()) {
								ps_value = rs.getDouble(1);
							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							close();
						}
						return (ps_value*220)/1000;
					}
					
					// 지난달 직원별 플러그 사용량 총합
						public double monthlyPowerE2(String id) {
							double ps_value = 0;
							try {
								connect();

								String sql = "SELECT sum(ps_value) FROM t_plug_sensing WHERE ps_datetime between sysdate - 60 and sysdate - 30 and plug_seq = plug_seq in (select plug_seq from t_plug where emp_id = ?)";

								psmt = conn.prepareStatement(sql);
								psmt.setString(1, id);
								rs = psmt.executeQuery();

								if (rs.next()) {
									ps_value = rs.getDouble(1);
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								close();
							}
							return (ps_value*220)/1000;
						}
		
			

}
