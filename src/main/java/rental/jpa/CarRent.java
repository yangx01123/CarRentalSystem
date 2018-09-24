package rental.jpa;

import java.sql.Timestamp;
import java.util.Calendar;

public class CarRent {
	private Integer id;
	private String cartype;
	private Timestamp startdt;
	private int days;
	private Timestamp enddt;

	public CarRent() {
		super();
	}

	public CarRent(Integer id, String cartype, Timestamp startdt, int days) {
		super();
		this.id = id;
		this.cartype = cartype;
		if (startdt == null) {
			startdt = new Timestamp(System.currentTimeMillis());
		}
		this.startdt = startdt;
		this.days = days;
		Timestamp timestamp = new Timestamp(startdt.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.DAY_OF_MONTH, days);
		this.enddt = new Timestamp(cal.getTime().getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public Timestamp getStartdt() {
		return startdt;
	}

	public void setStartdt(Timestamp startdt) {
		this.startdt = startdt;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Timestamp getEnddt() {
		return enddt;
	}

	public void setEnddt(Timestamp enddt) {
		this.enddt = enddt;
	}

	public boolean hasOverlap(CarRent cr) {
		long oldestStartMs = Math.min(startdt.getTime(), cr.getStartdt().getTime());
		long latestEndMs = Math.max(enddt.getTime(), cr.getEnddt().getTime());
		long totalGap = latestEndMs - oldestStartMs;
		long addedDaysMs = (days + cr.getDays()) * 86400000L;
		return totalGap < addedDaysMs;
	}

	@Override
	public String toString() {
		return String.format("CarRent [id=%s, cartype=%s, startdt=%s, days=%s, enddt=%s]", id, cartype, startdt, days,
				enddt);
	}
}
