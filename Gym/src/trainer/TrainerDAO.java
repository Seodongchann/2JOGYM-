package trainer;

import java.util.List;



public interface TrainerDAO {
	//모든 행 조회
	List<Trainer> selectAll();
	//PK로 검색
	Trainer findByPK(int no);
	
	//행 추가하기 : 추가된 행의 개수 반환
//	int insert(Products p);
	int insert(int Trainer_ID, String Trainer_Name, int Trainer_Phone
			, String Trainer_Gender, String Trainer_Birth
			, String Trainer_Address, int Enroll_Code);
	
	//행 수정하기 : 변경된 행의 개수 반환
//	int update(Products p);
	int update(int Trainer_ID, String Trainer_Name, int Trainer_Phone
			, String Trainer_Gender, String Trainer_Birh
			, String Trainer_Address, int Enroll_Code);
	
	//행 삭제하기 : 삭제된 행의 개수 반환
	int delete(int no);
	
}

