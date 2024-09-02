package memberShip;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipJoinMember {
	private int id;
	private String name;
	private String phone;
	private String gender;
	private String birth;
	private String address;
	private int enroll_code;
	private Date membership_StartDate;
	private Date membership_EndDate;
	private Date membership_EnrollDate;
}
