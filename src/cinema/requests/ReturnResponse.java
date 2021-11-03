package cinema.requests;

import cinema.objects.Seat;

public class ReturnResponse {

    private final Seat returned_ticket;

    public ReturnResponse(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }
}