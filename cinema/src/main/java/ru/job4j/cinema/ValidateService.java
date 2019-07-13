package ru.job4j.cinema;

import java.util.List;

public class ValidateService implements Validate {

    private final static Validate INSTANCE = new ValidateService();

    private final Store store = DatabaseStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Seat> getSeats() {
        return store.getSeats();
    }

    @Override
    public void makePayment(Visitor visitor, Seat seat) {
        if (validateSeat(seat)) {
            store.makePayment(visitor, seat);
        } else {
            throw new IllegalStateException("Wrong price or this place is already sold");
        }
    }

    private boolean validateSeat(Seat seat) {
        var result = true;
        Seat dbSeat = store.getSeat(seat);
        if (dbSeat.getPrice() != seat.getPrice() || dbSeat.isSold()) {
            result = false;
        }
        return result;
    }
}