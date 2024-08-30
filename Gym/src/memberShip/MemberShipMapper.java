package memberShip;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import members.ResultMapper;

public class MemberShipMapper implements ResultMapper<MemberShip> {

	@Override
	public MemberShip resultMapping(ResultSet rs) {
		try {
			int memberShip_id = rs.getInt("MemberShip_Id");
			int enroll_code = rs.getInt("Enroll_Code");
			int membership_Month = rs.getInt("MemberShip_Month");
			int membership_price = rs.getInt("MemberShip_Price");
			Date memberShip_StartDate = rs.getDate("MemberShip_StartDate");
			Date memberShip_EndDate = rs.getDate("MemberShip_EndDate");
			Date memberShip_EnrollDate = rs.getDate("MemberShip_EnrollDate");

			return MemberShip.builder().memberShip_id(memberShip_id).enroll_Code(enroll_code)
					.membership_Month(membership_Month).membership_Price(membership_price)
					.membership_StartDate(memberShip_StartDate).membership_EndDate(memberShip_EndDate)
					.membership_EnrollDate(memberShip_EnrollDate).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("메핑 중 예외발생", e);
		}
		
	}

}
