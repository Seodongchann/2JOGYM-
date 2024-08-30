package trainer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import attend.Mapper;
import dbutil.DBUtil;
import members.Member;
import members.MemberMapper;
import members.ResultMapper;

public class TrainerDAOImpl implements TrainerDAO {
	public static trainer.ResultMapper<Trainer> trainerMapper = new TrainerMapper();
	
	private Trainer resultMapping(ResultSet rs) throws SQLException {
		String Trainer_Name = rs.getString("Trainer_Name");
		String Trainer_Phone = rs.getString("Trainer_Phone");
		String Trainer_Gender = rs.getString("Trainer_Gender");
		String Trainer_Birth = rs.getString("Trainer_Birth");
		Date Trainer_Start_Date = rs.getDate("Trainer_Start_Date");
		String Trainer_Address = rs.getString("Trainer_Address");
		String Trainer_image = rs.getString("Trainer_image");

		return new Trainer(Trainer_Name, Trainer_Phone, Trainer_Gender, Trainer_Birth,
				Trainer_Address);
	}

	public int TrainerInsert(Trainer trainer) {
		String sql = "insert into Trainer(Trainer_Name,Trainer_Phone, Trainer_Gender\r\n"
				+ "				,Trainer_Birth "  + ",Trainer_Address\r\n" + "			) " 
				+ "Values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, trainer.getName());
			stmt.setString(2, trainer.getPhone());
			stmt.setString(3, trainer.getGender());
			stmt.setString(4, trainer.getBirth());
			stmt.setString(5, trainer.getAddress());

			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return result;
	}

	@Override
	public List<Trainer> TrainerselectAll() {
		String a = "Select * from trainer; ";
		List<Trainer> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(a);
			while (rs.next()) {
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
	public List<Trainer> trainerSelectName(String name) {
		String sql = "select * from trainer where trainer_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Trainer> list = new ArrayList<Trainer>();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(trainerMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int TrainerUpdate(Trainer trainer) {
		String sql = "update trainer set Trainer_Name = ?, Trainer_Phone = ?, Trainer_Gender = ?,"
				+ " Trainer_Birth= ?, Trainer_Address = ?, Trainer_Start_Date = ?";
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
			stmt.setDate(6, trainer.getStart_date());
			
			result = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		return result;
	}	
	
	

}
