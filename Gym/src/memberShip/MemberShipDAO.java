package memberShip;

public interface MemberShipDAO {
	public int membershipInsert(MemberShip memberShip);  // 회원권 등록
	public MemberShip memberShipSelect(int Enroll_Code);
}
