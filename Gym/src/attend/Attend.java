package attend;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class Attend {
	private int attendEnroll_Code;
	private LocalDateTime enter_Time;
	private LocalDateTime exit_Time;
	
	
}
