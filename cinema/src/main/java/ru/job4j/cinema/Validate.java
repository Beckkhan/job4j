package ru.job4j.cinema;

import java.util.List;

public interface Validate {

    List<Seat> getSeats();

    void makePayment(Visitor visitor, Seat seat);
}
