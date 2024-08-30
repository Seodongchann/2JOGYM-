package pt;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface PTDAO {		
		//행 추가하기 : 추가된 행의 개수 반환
//		int insert(Products p);

		int insert(PT pt);
		List<PT> selectTime(LocalDateTime PT_Time); //메서드의 형태
		List<PT> selectDate(Date PT_Date);
		List<PT> selectPriceAndDate(int PT_Price, Date PT_Date);
		List<PT> selectAll();
		List<PT> selectMonthPrice(int PT_Price, Date PT_Date);
		
}
