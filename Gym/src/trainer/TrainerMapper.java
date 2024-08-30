package trainer;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class TrainerMapper implements ResultMapper<Trainer> {
		@Override
		public Trainer resultMapping(ResultSet rs) {
			try {
				int id = rs.getInt("Trainer_id");
				String name = rs.getString("Trainer_Name");
				String phone = rs.getString("Trainer_phone");
				String gender = rs.getString("Trainer_gender");
				String birth = rs.getString("Trainer_birth");
				String address = rs.getString("Trainer_address");
				Date Start_Date = rs.getDate("Trainer_Start_Date");
				String trainer_image = rs.getString("Trainer_image");

				return Trainer.builder().id(id).name(name).phone(phone).gender(gender).birth(birth).address(address)
						.start_date(Start_Date).trainer_image(trainer_image).build();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("메핑 중 예외발생", e);
			}
		}
	}


