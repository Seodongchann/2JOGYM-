package trainer;

import java.sql.ResultSet;

public interface ResultMapper<T> {
	T resultMapping(ResultSet rs);

}
