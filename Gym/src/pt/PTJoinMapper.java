package pt;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import members.ResultMapper;

public class PTJoinMapper implements ResultMapper<PTMemberTrainer> {

	@Override
	public PTMemberTrainer resultMapping(ResultSet rs) {
		try {

			String name = rs.getString("b.member_name");
			String mPhone = rs.getString("b.member_phone");
			String gender = rs.getString("b.member_gender");
			String birth = rs.getString("b.member_birth");
			int enroll_code = rs.getInt("b.enroll_code");
			int t_id = rs.getInt("c.trainer_id");
			String tName = rs.getString("c.trainer_name");
			String tPhone = rs.getString("c.trainer_phone");
			String tGender = rs.getString("c.trainer_gender");
			String tBirth = rs.getString("c.trainer_birth");
			int pt_code = rs.getInt("a.pt_code");
			Time pt_Time = rs.getTime("a.pt_time");
			Date pt_date = rs.getDate("a.pt_date");

			return PTMemberTrainer.builder().name(name).mPhone(mPhone).gender(gender).brith(birth)
					.enroll_code(enroll_code).t_id(t_id).tName(tName).tPhone(tPhone).tGender(tGender).tBirth(tBirth)
					.pt_code(pt_code).pt_time(pt_Time).pt_date(pt_date). build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("메핑 중 예외발생", e);
		}
	}
}
