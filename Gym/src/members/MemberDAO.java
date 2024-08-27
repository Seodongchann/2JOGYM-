package members;

import java.sql.ResultSet;
import java.util.List;

import memberShip.MemberShip;

public interface MemberDAO {
	public int memberInsert(Member member);// 회원등록

	public int memberUpdate(Member member); // 회원 정보 수정

	public Member memberSelectCode(int Enroll_Code); // 등록 코드로 조회

	public List<Member> memberSelectName(String name); // 회원이름으로 조회

	public List<Member> memberSelectAll(); // 회원정보 전체 조회

	public int memberDelete(int Enroll_Code);
}
