package trainer;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Trainer {
	private int id;
	private String name;
	private String phone;
	private String gender;
	private String birth;
	private Date start_date;
	private String address;
	private String trainer_image;

	public Trainer(String name, String phone, String gender, String birth, Date start_date, String address,
			String trainer_image) {
		super();
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.start_date = start_date;
		this.address = address;
		this.trainer_image = trainer_image;
	}

	public Trainer(String name, String phone, String gender, String birth, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
	}
	public Trainer(int id,String name, String phone, String gender, String birth, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
	}
	

}
