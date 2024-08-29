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

	// PT 등록
	@Override
	public int insert(PT pt) {
		String sql = "insert into pt(int PT_Trainer_ID, int PT_Member_ID, LocalDateTime PT_Time, int PT_Price, Date PT_EnrollDate,Date PT_Date)"
				+ "Values(?,?,?,?,?) ";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pt.getPT_Trainer_ID());
			stmt.setInt(2, pt.getPT_Member_ID());
			stmt.setTimestamp(3, Timestamp.valueOf(pt.getPT_Time()));
			stmt.setInt(4, pt.getPT_Price());
			stmt.setDate(5, pt.getPT_EnrollDate());
			result = stmt.executeUpdate();
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
	public List<PT> DatePriceAndDate(int PT_Price, Date PT_Date) {
		String sql = "select date(?) as d, sum(?) as price \r\n" + 
				"from pt group by d";
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


}