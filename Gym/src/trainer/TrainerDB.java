package trainer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class TrainerDB implements TrainerDAO {

	private Trainer resultMapping(ResultSet rs) throws SQLException {
		String Trainer_Name = rs.getString("Trainer_Name");
		String Trainer_Phone = rs.getString("Trainer_Phone");
		String Trainer_Gender = rs.getString("Trainer_Gender");
		String Trainer_Birth = rs.getString("Trainer_Birth");
		Date Trainer_Start_Date = rs.getDate("Trainer_Start_Date");
		String Trainer_Address = rs.getString("Trainer_Address");
		String Trainer_image = rs.getString("Trainer_image");

		return new Trainer(Trainer_Name, Trainer_Phone, Trainer_Gender, Trainer_Birth, Trainer_Start_Date,
				Trainer_Address, Trainer_image);
	}

	public int insert(Trainer trainer) {
		String sql = "insert into Trainer(Trainer_Name,Trainer_Phone, Trainer_Gender\r\n"
				+ "				,Trainer_Birth" + ",Trainer_Address\r\n"
				+ "			) " + "Values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, trainer.getName());
			stmt.setString(2, trainer.getPhone());
			stmt.setString(3, trainer.getGender());
			stmt.setString(4, trainer.getBirth());
			stmt.setString(5, trainer.getAddress());
	//		stmt.setString(7, trainer.getTrainer_image());
			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return result;
	}

	@Override
	public List<Trainer> selectAll() {
		String a = "Select * from trainer; ";
		List<Trainer> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection("gym");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(a);
			while(rs.next()) {
				Trainer trainer = resultMapping(rs);
				list.add(trainer);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Trainer findByPK(int no) {
		String a = "Select * from trainer; ";
		Connection conn = null;
		PreparedStatement stmt = null;
		return null;
	}

	@Override
	public int insert(int Member_ID, String Member_Name, int Member_Phone, String Member_Gender, String Member_Birth,
			String Member_Address, int Enroll_Code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int Member_ID, String Member_Name, int Member_Phone, String Member_Gender, String Member_Birh,
			String Member_Address, int Enroll_Code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
