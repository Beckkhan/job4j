package ru.job4j.cinema;

import java.util.List;

public interface Store {

    List<Seat> getSeats();

    Seat getSeat(Seat seat);

    boolean makePayment(Visitor visitor, Seat seat);
}
