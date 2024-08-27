package members;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String name;
	private int phone;
	private String gender;
	private String birth;
	private String address;
	private int enroll_code;
	private String member_image;
	
	
}
