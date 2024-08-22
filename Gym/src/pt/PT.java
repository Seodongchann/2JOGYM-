package pt;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PT {
	private int trainer_Id;
	private int member_Id;
	private LocalDateTime pt_Time;
	private int pt_Price;
	private LocalDate pt_EnrollDate;
	
}
