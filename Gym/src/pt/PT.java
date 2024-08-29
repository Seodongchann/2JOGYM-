package pt;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PT {
	private int PT_Trainer_ID;
	private int PT_Member_ID;
	private LocalDateTime PT_Time;
	private int PT_Price;
	private Date PT_EnrollDate;
	private Date PT_Date;
	

}
