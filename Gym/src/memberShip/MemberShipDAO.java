package memberShip;

import java.sql.Date;
import java.util.List;

public interface MemberShipDAO {
	public int membershipInsert(MemberShip memberShip); // 회원권 등록

	public MemberShip memberShipSelect(int Enroll_Code);

	public List<MembershipJoinMember> joinMembers();

	public List<MembershipJoinMember> selectMembership(int enroll_code);

	public MembershipJoinMember selectMembership(int enroll_code, Date startDate);
	
	public MembershipJoinMember selectMemberships(int id);
	
	

}
