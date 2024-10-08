package members;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements ResultMapper<Member> {
	@Override
	public Member resultMapping(ResultSet rs) {
		try {
			int id = rs.getInt("Member_id");
			String name = rs.getString("Member_Name");
			String phone = rs.getString("Member_phone");
			String gender = rs.getString("Member_gender");
			String birth = rs.getString("Member_birth");
			String address = rs.getString("Member_address");
			int enroll_code = rs.getInt("Enroll_Code");
			String member_image = rs.getString("Member_image");

			return Member.builder().id(id).name(name).phone(phone).gender(gender).birth(birth).address(address)
					.enroll_code(enroll_code).member_image(member_image).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("메핑 중 예외발생", e);
		}
	}
}
