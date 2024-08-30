package memberShip;

import java.util.Date;
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

	public MemberShip(int enroll_Code, int membership_Month, int membership_Price, Date membership_StartDate,
			Date membership_EndDate) {
		super();
		this.enroll_Code = enroll_Code;
		this.membership_Month = membership_Month;
		this.membership_Price = membership_Price;
		this.membership_StartDate = membership_StartDate;
		this.membership_EndDate = membership_EndDate;
	}

}
