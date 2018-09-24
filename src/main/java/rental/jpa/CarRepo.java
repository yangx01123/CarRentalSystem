package rental.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepo {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class CarRowMapper implements RowMapper<Car> {
		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
			Car car = new Car();
			car.setCartype(rs.getString("cartype"));
			car.setAvailable(rs.getInt("available"));
			return car;
		}
	}

	public List<Car> findAll() {
		return jdbcTemplate.query("select * from car", new CarRowMapper());
	}

	public List<Car> findByType(String cartype) {
		return jdbcTemplate.query("select * from car where cartype=?", new Object[] { cartype }, new CarRowMapper());
	}

}
