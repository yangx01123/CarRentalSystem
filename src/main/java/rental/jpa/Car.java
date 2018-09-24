package rental.jpa;

public class Car {
	private String cartype;
	private int available;

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return String.format("Car [cartype=%s, available=%d]", cartype, available);
	}
}
