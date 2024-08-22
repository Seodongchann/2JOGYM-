package trainer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Trainer {
	private int name;
	private int phone;
	private String gender;
	private String birth;
	private LocalDate start_date;
	private String address;
	
}
