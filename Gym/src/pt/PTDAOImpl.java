package pt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;
import memberShip.MemberShip;
import members.ResultMapper;

public class PTDAOImpl implements PTDAO {
	public static ResultMapper<PT> PTMapper = new PTMapper();
	private ResultMapper<PTMemberTrainer> ptJoinMapper = new PTJoinMapper();

	// PT 등록
	@Override
	public int insert(PT pt, int num) {
		String sql = "insert into pt(Trainer_ID,Member_ID)" + "Values(?,?) ";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			// stmt.setTimestamp(3, Timestamp.valueOf(pt.getPT_Time()));
			// stmt.setInt(4, pt.getPT_Price());
			// stmt.setDate(4, pt.getPT_EnrollDate());
			for (int i = 0; i < num; i++) {
				stmt.setInt(1, pt.getPT_Trainer_ID());
				stmt.setInt(2, pt.getPT_Member_ID());
				result += stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);

		}
		return result;
	}

	@Override
	public List<PT> selectTime(LocalDateTime PT_Time) {
		String sql = "Select * from pt where PT_Time = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<PT> list = new ArrayList<PT>();
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, Timestamp.valueOf(PT_Time));
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(PTMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return null;
	}

	@Override
	public List<PT> selectDate(Date PT_Date) {
		String sql = "Select * from pt where PT_Date = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<PT> list = new ArrayList<PT>();
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, PT_Date);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(PTMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return list;

	}

	@Override
	public List<PT> selectAll() {
		String sql = "Select * from pt";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<PT> list = new ArrayList<PT>();
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(PTMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return list;

	}

	@Override
	public List<PT> selectPriceAndDate(int PT_Price, Date PT_Date) {
		String sql = "select date(?) as d, sum(?) as price \r\n" + "from pt group by d";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<PT> list = new ArrayList<PT>();
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, PT_Date);
			stmt.setInt(2, PT_Price);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(PTMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public List<PT> selectMonthPrice(int PT_Price, Date PT_Date) {
		String sql = "select month(?) as mon, sum(?) as price \r\n" + "from pt group by mon with rollup";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<PT> list = new ArrayList<PT>();
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, PT_Date);
			stmt.setInt(2, PT_Price);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(PTMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public List<PTMemberTrainer> selectJoinAll() {
		String sql = "select b.member_name,b.member_phone, b.member_gender,b.member_birth,b.enroll_code,c.trainer_id, c.trainer_name,c.trainer_phone,c.trainer_gender,c.trainer_birth,a.pt_code, a.pt_time,a.pt_date from pt as a left join member as b on a.member_id = b.Enroll_code\r\n"
				+ "left join trainer as c on a.trainer_id = c.trainer_id; ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PTMemberTrainer> pts = new ArrayList<PTMemberTrainer>();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pts.add(ptJoinMapper.resultMapping(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pts;
	}

	@Override
	public int updatePT(PT pt) {
		String sql = "update PT set Trainer_id=?,Member_id = ?, PT_time = ?, PT_date=?where PT_Code =?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pt.getPT_Trainer_ID());
			stmt.setInt(2, pt.getPT_Member_ID());
			stmt.setTime(3, pt.getPT_Time());
			stmt.setDate(4, pt.getPT_Date());
			stmt.setInt(5, pt.getPT_Code());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);

		}
		return result;
	}

	@Override
	public PTMemberTrainer selelctJoinID(int id) {
		String sql = "select b.member_name,b.member_phone, b.member_gender,b.member_birth,b.enroll_code,c.trainer_id, c.trainer_name,c.trainer_phone,c.trainer_gender,c.trainer_birth,a.pt_code, a.pt_time, a.pt_date from pt as a left join member as b on a.member_id = b.Enroll_code\r\n"
				+ "left join trainer as c on a.trainer_id = c.trainer_id where a.pt_code=?; ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PTMemberTrainer pmt = null;
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				pmt = ptJoinMapper.resultMapping(rs);
			}
			return pmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return null;
	}

	@Override
	public int deletePt(int pt_code) {
		String sql = "delete from pt where pt_code=?;";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pt_code);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		return result;
	}

	@Override
	public List<PTMemberTrainer> selectjoinName(String name) {
		String sql = "select b.member_name,b.member_phone, b.member_gender,b.member_birth,b.enroll_code,c.trainer_id, c.trainer_name,c.trainer_phone,c.trainer_gender,c.trainer_birth,a.pt_code, a.pt_time, a.pt_date from pt as a left join member as b on a.member_id = b.Enroll_code\r\n"
				+ "left join trainer as c on a.trainer_id = c.trainer_id where b.member_name=?; ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PTMemberTrainer> list = new ArrayList<PTMemberTrainer>();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(ptJoinMapper.resultMapping(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}