package attend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class AttendDAOImpl {
	public static Mapper<Attend> attendMapper = new AttendMapper();

	// 입장 등록
	public int insertEnter(Attend attend) {
		String sql = "INSERT INTO ATTEND (attendEnroll_Code, Attend_Date, Enter_Time) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("GYM");
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, attend.getAttendEnroll_Code());
			stmt.setDate(2, java.sql.Date.valueOf(attend.getAttend_Date()));
			stmt.setTimestamp(3, Timestamp.valueOf(attend.getEnter_Time()));

			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		return result;
	}

	// 퇴장 등록
	public int insertExit(Attend attend) {
	    // 쿼리 문자열 수정: EXIT_TIME을 업데이트할 조건을 명확히 설정
	    String sql = "UPDATE ATTEND SET Exit_Time = ? WHERE attendEnroll_Code = ? AND Attend_Date = ? AND Exit_Time IS NULL";

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    int result = 0;

	    try {
	        conn = DBUtil.getConnection("GYM");
	        stmt = conn.prepareStatement(sql);

	        // Exit_Time이 null인지 확인
	        if (attend.getExit_Time() == null) {
	            throw new IllegalArgumentException("Exit_Time cannot be null");
	        }

	        // Exit_Time을 설정
	        stmt.setTimestamp(1, Timestamp.valueOf(attend.getExit_Time()));
	        // attendEnroll_Code과 Attend_Date로 레코드를 찾기 위한 조건 설정
	        stmt.setInt(2, attend.getAttendEnroll_Code());
	        stmt.setDate(3, java.sql.Date.valueOf(attend.getAttend_Date()));

	        result = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("퇴장 기록 업데이트 중 오류 발생", e);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Invalid argument: " + e.getMessage(), e);
	    } finally {
	        DBUtil.closeAll(null, stmt, conn);
	    }

	    return result;
	}
	
	// 특정 날짜의 입장 및 퇴장 로그 조회
    public List<Attend> getAttendLogsByDate(int enrollCode, LocalDate date) {
        List<Attend> logs = new ArrayList<>();
        String sql = "SELECT * FROM ATTEND WHERE attendEnroll_Code = ? AND attend_Date = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection("GYM");
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enrollCode);
            stmt.setDate(2, java.sql.Date.valueOf(date));

            rs = stmt.executeQuery();
            while (rs.next()) {
                int attendEnroll_Code = rs.getInt("attendEnroll_Code");
                LocalDate attend_Date = rs.getDate("attend_Date").toLocalDate();
                LocalDateTime enter_Time = rs.getTimestamp("enter_Time").toLocalDateTime();
                LocalDateTime exit_Time = rs.getTimestamp("exit_Time").toLocalDateTime();

                Attend log = new Attend(attendEnroll_Code, attend_Date, enter_Time, exit_Time);
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, stmt, conn);
        }
        
        return logs;
    }
}