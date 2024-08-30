package attend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendMapper implements Mapper<Attend> {

    @Override
    public Attend mapping(ResultSet rs) {
        try {
            int attendEnroll_Code = rs.getInt("AttendEnroll_Code");
            LocalDate attend_Date = rs.getDate("Attend_Date").toLocalDate();
            LocalDateTime enter_Time = rs.getTimestamp("Enter_Time").toLocalDateTime();
            LocalDateTime exit_Time = rs.getTimestamp("Exit_Time").toLocalDateTime();
            
            return Attend.builder()
                    .attendEnroll_Code(attendEnroll_Code)
                    .attend_Date(attend_Date)
                    .enter_Time(enter_Time)
                    .exit_Time(exit_Time)
                    .build();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("맵핑 중 예외 발생", e);
        }
    }
}