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
	private int id;
	private String name;
	private String phone;
	private String gender;
	private String birth;
	private String address;
	private int enroll_code;
	private String member_image;

	public Member(String name, String phone, String gender, String birth, String address, int enroll_code,
			String member_image) {
		super();
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
		this.enroll_code = enroll_code;
		this.member_image = member_image;
	}

}
