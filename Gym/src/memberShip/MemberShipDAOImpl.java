package memberShip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;
import members.ResultMapper;

public class MemberShipDAOImpl implements MemberShipDAO {
	private static ResultMapper<MemberShip> mResultMapper = new MemberShipMapper();
	// 회원권 등록
	@Override
	public int membershipInsert(MemberShip memberShip) {
		String sql = "insert into MemberShip (MemberShip _Enroll_code, MemberShip_Month, MemberShip_Price, MemberShip_StartDate, MemberShip_endDate) values (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, memberShip.getEnroll_Code());
			stmt.setInt(2, memberShip.getMembership_Month());
			stmt.setInt(3, memberShip.getMembership_Price());
			stmt.setDate(4, memberShip.getMembership_StartDate());
			stmt.setDate(5, memberShip.getMembership_EndDate());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		// TODO Auto-generated method stub
		return result;
	}
	// 등록 코드로 회원권 조회
	@Override
	public MemberShip memberShipSelect(int Enroll_Code) {
		String sql = "select * from MemberShip where Enroll_Code = ? ;";
		Connection conn = null;
		PreparedStatement stmt =  null;
		ResultSet rs = null;
		MemberShip memberShip = new MemberShip();
		
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql); 
			stmt.setInt(1, Enroll_Code);
			rs = stmt.executeQuery();
			if(rs.next()) {
				memberShip = mResultMapper.resultMapping(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
}
