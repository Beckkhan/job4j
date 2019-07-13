package ru.job4j.cinema;

public class Visitor {

    private String name;
    private String phone;
    private Seat seat;

    public Visitor(String name, String phone, Seat seat) {
        this.name = name;
        this.phone = phone;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}