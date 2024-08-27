package memberShip;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberShip {
	private int memberShip_id;
	private int enroll_Code;
	private int membership_Month;
	private int membership_Price;
	private Date membership_StartDate;
	private Date membership_EndDate;
	private Date membership_EnrollDate;
}
