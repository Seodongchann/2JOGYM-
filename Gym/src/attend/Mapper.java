package attend;

import java.sql.ResultSet;

public interface Mapper<T> {
	T mapping(ResultSet rs);
}
