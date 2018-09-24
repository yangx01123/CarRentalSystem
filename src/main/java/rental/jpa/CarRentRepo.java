package rental.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CarRentRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class CarRentRowMapper implements RowMapper<CarRent> {
        @Override
        public CarRent mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarRent carrent = new CarRent();
            carrent.setId(rs.getInt("id"));
            carrent.setCartype(rs.getString("cartype"));
            carrent.setStartdt(rs.getTimestamp("startdt"));
            carrent.setDays(rs.getInt("days"));
            carrent.setEnddt(rs.getTimestamp("enddt"));
            return carrent;
        }
    }

    /**
     * Find all reservation IDs.
     *
     * @return a list of IDs.
     */
    public List<Integer> findAllId() {
        return jdbcTemplate.query("select id from carrent", new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                return rs.getInt(1);
            }
        });
    }

    /**
     * Find all reservations.
     *
     * @return a list of reservations.
     */
    public List<CarRent> findAll() {
        return jdbcTemplate.query("select * from carrent",
                new BeanPropertyRowMapper<CarRent>(CarRent.class));
    }

    /**
     * Find a reservation by ID.
     *
     * @param id reservation ID.
     * @return a reservation with the given ID.
     */
    public List<CarRent> findById(long id) {
        return jdbcTemplate.query("select * from carrent where id=?", new Object[]{id},
                new BeanPropertyRowMapper<CarRent>(CarRent.class));
    }

    /**
     * Find reservations by car type.
     *
     * @param cartype car type.
     * @return a list of reservations of the car type.
     */
    public List<CarRent> findByType(String cartype) {
        return jdbcTemplate.query("select * from carrent where cartype=?", new Object[]{cartype},
                new BeanPropertyRowMapper<CarRent>(CarRent.class));
    }

    /**
     * Delete a reservation by its ID.
     *
     * @param id reservation ID.
     * @return
     */
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from carrent where id=?", new Object[]{id});
    }

    public int insert(CarRent carrent) {
        return jdbcTemplate.update("insert into carrent (id, cartype, startdt, days, enddt) " + "values(?, ?, ?, ?, ?)",
                carrent.getId(), carrent.getCartype(), carrent.getStartdt(), carrent.getDays(),
                carrent.getEnddt());
    }

    public int update(CarRent carrent) {
        return jdbcTemplate.update(
                "update carrent " + " set cartype = ?, startdt = ?, days = ?, enddt = ? " + " where id = ?",
                carrent.getCartype(), carrent.getStartdt(), carrent.getDays(), carrent.getEnddt(),
                carrent.getId());
    }
}
