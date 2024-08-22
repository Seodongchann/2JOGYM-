package memberShip;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberShip {
	private int enroll_Code;
	private int membership_Month;
	private int membership_Price;
	private LocalDateTime membership_StartDate;
	private LocalDateTime membership_EndDate;
	private LocalDate membership_EnrollDate;
	
	
}
