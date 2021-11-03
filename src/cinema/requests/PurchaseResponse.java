package cinema.requests;

import cinema.objects.Seat;

public final class PurchaseResponse {

    private final String token;
    private final Seat ticket;

    public PurchaseResponse(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
