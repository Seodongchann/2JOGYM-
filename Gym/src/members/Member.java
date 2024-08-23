package members;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Member {
	private int id;
	private String name;
	private int phone;
	private String gender;
	private String birth;
	private String address;
	private int enroll_code;
	private String member_image;
	
	
}
