package trainer;

import java.util.List;

import members.Member;



public interface TrainerDAO {

	
	public int TrainerInsert(Trainer trainer);// 트레이너 등록

	public int TrainerUpdate(Trainer trainer); // 트레이너 정보 수정

	public int deleteTrainer(int id);
	
	public List<Trainer> TrainerselectAll(); // 트레이너 전체 조회
	
	public List<Trainer> trainerSelectName(String name); //트레이너 이름 조회
	
	public Trainer trainerSelectId(int trainer_id);
}

