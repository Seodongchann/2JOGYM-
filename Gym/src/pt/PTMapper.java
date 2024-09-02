package pt;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import members.ResultMapper;

public class PTMapper implements ResultMapper<PT> {

	private int PT_Trainer_ID;
	private int PT_Member_ID;
	private Time PT_Time;
	private Date PT_EnrollDate;
	private Date PT_Date;

	@Override
	public PT resultMapping(ResultSet rs) {
		try {
			int PT_Trainer_ID = rs.getInt("PT_Trainer_ID");
			int PT_Member_ID = rs.getInt("PT_Member_ID");
			// LocalDateTime PT_Time = rs.getTimestamp("PT_Time").toLocalDateTime();
			Time PT_Time = rs.getTime("PT_Time");
			int PT_Price = rs.getInt("PT_Price");
			Date PT_EnrollDate = rs.getDate("PT_EnrollDate");
			Date PT_Date = rs.getDate("PT_Date");
			return PT.builder().PT_Trainer_ID(PT_Trainer_ID).PT_Member_ID(PT_Member_ID).PT_Time(PT_Time)
					.PT_EnrollDate(PT_EnrollDate).PT_Date(PT_Date).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("메핑 중 예외발생", e);
		}
	}
}
