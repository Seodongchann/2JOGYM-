package pt;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PTMemberTrainer {
	private String name;
	private String mPhone;
	private String gender;
	private String brith;
	private int enroll_code;
	private int t_id;
	private String tName;
	private String tPhone;
	private String tGender;
	private String tBirth;
	private int pt_code;
	private Time pt_time;
	private Date pt_date;

}
