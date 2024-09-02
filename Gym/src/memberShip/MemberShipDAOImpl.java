package memberShip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;
import members.ResultMapper;

public class MemberShipDAOImpl implements MemberShipDAO {
	private static ResultMapper<MemberShip> mResultMapper = new MemberShipMapper();
	private MemberShipJoinMemberMapper mapper = new MemberShipJoinMemberMapper();
	List<MembershipJoinMember> list = new ArrayList<MembershipJoinMember>();

	// 회원권 등록
	@Override
	public int membershipInsert(MemberShip memberShip) {
		String sql = "insert into MemberShip (MemberShip_Enroll_code, MemberShip_Month, MemberShip_Price, MemberShip_StartDate, MemberShip_endDate) values (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			long milliSeconds = memberShip.getMembership_StartDate().getTime();
			long milliSeconds2 = memberShip.getMembership_EndDate().getTime();
			java.sql.Date startDate = new java.sql.Date(milliSeconds);
			java.sql.Date endDate = new java.sql.Date(milliSeconds2);
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, memberShip.getEnroll_Code());
			stmt.setInt(2, memberShip.getMembership_Month());
			stmt.setInt(3, memberShip.getMembership_Price());
			stmt.setDate(4, startDate);
			stmt.setDate(5, endDate);

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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MemberShip memberShip = new MemberShip();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Enroll_Code);
			rs = stmt.executeQuery();
			if (rs.next()) {
				memberShip = mResultMapper.resultMapping(rs);
			}
			return memberShip;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 조인해서 가져오기
	@Override
	public List<MembershipJoinMember> joinMembers() {

		String sql = "select  a.member_name, a.member_phone, a.member_gender, a.member_birth, a.member_address,a.enroll_code, b.membership_startdate, b.membership_enddate,b.membership_enrolldate from member as a left join membership as b on a.enroll_code = b.membership_enroll_code;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(mapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public List<MembershipJoinMember> selectMembership(int enroll_code) {
		List<MembershipJoinMember> m = new ArrayList<MembershipJoinMember>();
		String sql = "select  a.member_name, a.member_phone, a.member_gender, a.member_birth, a.member_address,a.enroll_code, b.membership_startdate, b.membership_enddate,b.membership_enrolldate from member as a left join membership as b on a.enroll_code = b.membership_enroll_code where a.enroll_code= ? ;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enroll_code);
			rs = stmt.executeQuery();
			while (rs.next()) {
				m.add(mapper.resultMapping(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return m;
	}

	@Override
	public MembershipJoinMember selectMembership(int enroll_code, Date startDate) {
		String sql = "select  a.member_name, a.member_phone, a.member_gender, a.member_birth, a.member_address,a.enroll_code, b.membership_startdate, b.membership_enddate,b.membership_enrolldate from member as a left join membership as b on a.enroll_code = b.membership_enroll_code where a.enroll_code= ? and b.membership_startDate = ?;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enroll_code);
			stmt.setDate(2, startDate);
			rs = stmt.executeQuery();
			if (rs.next()) {
				MembershipJoinMember m = mapper.resultMapping(rs);
				return m;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

		return null;
	}
}
