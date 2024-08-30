package members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class MemberDAOImpl implements MemberDAO {
	public static ResultMapper<Member> memberMapper = new MemberMapper();

	// 회원 등록
	@Override
	public int memberInsert(Member member) {
		String sql = "insert into member (Member_Name, Member_Phone, Member_Gender, Member_Birth, Member_Address, Enroll_Code, Member_image) values(?,?,?,?,?,?,?);";

		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getPhone());
			stmt.setString(3, member.getGender());
			stmt.setString(4, member.getBirth());
			stmt.setString(5, member.getAddress());
			stmt.setInt(6, member.getEnroll_code());
			stmt.setString(7, member.getMember_image());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		return result;
	}

	// 회원 정보 수정
	@Override
	public int memberUpdate(Member member) {
		String sql = "update member set Member_Name = ?, Member_Phone = ?, Member_Gender = ?, Member_Birth= ?, Member_Address = ?, Member_image = ? where Enroll_Code = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getPhone());
			stmt.setString(3, member.getGender());
			stmt.setString(4, member.getBirth());
			stmt.setString(5, member.getAddress());
			stmt.setString(6, member.getMember_image());
			stmt.setInt(7, member.getEnroll_code());
			result = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}

		return result;
	}

	// 등록 코드로 회원 찾기
	@Override
	public Member memberSelectCode(int enroll_code) {
		String sql = "select * from member where enroll_code = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = new Member();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, enroll_code);
			rs = stmt.executeQuery();

			if (rs.next()) {
				member = memberMapper.resultMapping(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}

	// 회원이름으로 정보찾기
	@Override
	public List<Member> memberSelectName(String name) {
		String sql = "select * from member where Member_Name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(memberMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 회원정보 전체조회
	@Override
	public List<Member> memberSelectAll() {
		String sql = "select * from member";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();

		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(memberMapper.resultMapping(rs));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// 등록 코드로 회원 삭제
	@Override
	public int memberDelete(int Enroll_Code) {
		String sql = "delete from member where Enroll_Code = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection("gym");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Enroll_Code);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
}
