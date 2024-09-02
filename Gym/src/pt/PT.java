package pt;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PT {
	private int PT_Code;
	private int PT_Trainer_ID;
	private int PT_Member_ID;
	private Time PT_Time;
	private Date PT_EnrollDate;
	private Date PT_Date;

	public PT(int pT_Trainer_ID, int pT_Member_ID) {
		super();
		PT_Trainer_ID = pT_Trainer_ID;
		PT_Member_ID = pT_Member_ID;
	}

	public PT(int pt_code, int pT_Trainer_ID, int pT_Member_ID, Time pT_Time, Date pT_Date) {
		super();
		PT_Code = pt_code;
		PT_Trainer_ID = pT_Trainer_ID;
		PT_Member_ID = pT_Member_ID;
		PT_Time = pT_Time;
		PT_Date = pT_Date;
	}

}
