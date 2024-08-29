package attend;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class Attend {
	private int attendEnroll_Code;
	private LocalDate attend_Date;
	private LocalDateTime enter_Time;
	private LocalDateTime exit_Time;

	public Attend(int attendEnroll_Code, LocalDate attend_Date, LocalDateTime enter_Time) {
		super();
		this.attendEnroll_Code = attendEnroll_Code;
		this.attend_Date = attend_Date;
		this.enter_Time = enter_Time;
	}

}
