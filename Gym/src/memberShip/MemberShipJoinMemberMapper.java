package memberShip;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import members.ResultMapper;

public class MemberShipJoinMemberMapper implements ResultMapper<MembershipJoinMember> {
	@Override
	public MembershipJoinMember resultMapping(ResultSet rs) {
		try {
			int id = rs.getInt("a.member_id");
			String name = rs.getString("a.member_name");
			String phone = rs.getString("a.member_phone");
			String gender = rs.getString("a.Member_gender");
			String birth = rs.getString("a.Member_birth");
			String address = rs.getString("a.Member_address");
			int enroll_code = rs.getInt("a.Enroll_Code");
			Date memberShip_StartDate = rs.getDate("b.MemberShip_StartDate");
			Date memberShip_EndDate = rs.getDate("b.MemberShip_EndDate");
			Date memberShip_EnrollDate = rs.getDate("b.MemberShip_EnrollDate");

			return MembershipJoinMember.builder().id(id). name(name).phone(phone).gender(gender).birth(birth).address(address)
					.enroll_code(enroll_code).membership_StartDate(memberShip_StartDate)
					.membership_EndDate(memberShip_EndDate).membership_EnrollDate(memberShip_EnrollDate).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("메핑 중 예외발생", e);
		}
	}
}
