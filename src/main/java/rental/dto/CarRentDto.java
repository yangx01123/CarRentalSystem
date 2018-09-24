package rental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class CarRentDto {
    private Long id;
    private String cartype;
    private Timestamp startdt;
    private int days;
    private Timestamp enddt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
