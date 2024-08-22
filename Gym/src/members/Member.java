package members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Member {
	private int id;
	private int name;
	private int phone;
	private int gender;
	private String birth;
	private String address;
	private int enroll_code;
	
}
