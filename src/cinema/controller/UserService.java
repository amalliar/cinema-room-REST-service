package cinema.controller;

import cinema.objects.Cinema;
import cinema.objects.Seat;
import cinema.requests.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class UserService {

    private final Cinema cinema;
    private final Map<String, Seat> takenSeats;

    public UserService(Cinema cinema) {
        this.cinema = cinema;
        this.takenSeats = new HashMap<>();
    }

    public ResponseEntity<Cinema> getSeats() {
        return new ResponseEntity<>(
                cinema, HttpStatus.OK
        );
    }

    public ResponseEntity<PurchaseResponse> purchaseTicket(
            PurchaseRequest request) {
        if (request.getRow() < 1 || request.getRow() > cinema.getTotal_rows() ||
                request.getColumn() < 1 || request.getColumn() > cinema.getTotal_columns()) {
            throw new RequestException(
                    "The number of a row or a column is out of bounds!");
        }

        for (Seat seat : cinema.getAvailable_seats()) {
            if (seat.getRow() == request.getRow() &&
                    seat.getColumn() == request.getColumn()) {
                cinema.getAvailable_seats().remove(seat);
                PurchaseResponse response = new PurchaseResponse(
                        UUID.randomUUID().toString(), seat
                );
                takenSeats.put(response.getToken(), response.getTicket());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        throw new RequestException(
                "The ticket has been already purchased!");
    }

    public ResponseEntity<ReturnResponse> returnTicket(
            ReturnRequest request) {
        Seat toReturn = takenSeats.get(request.getToken());
        if (toReturn == null) {
            throw new RequestException("Wrong token!");
        }

        List<Seat> allSeats = cinema.getAvailable_seats();
        int toReturnSeqNum = toReturn.getRow() * cinema.getTotal_columns()
                + toReturn.getColumn();
        int idx;
        for (idx = 0; idx < allSeats.size(); ++idx) {
            Seat current = allSeats.get(idx);
            int currentSeqNum = current.getRow() * cinema.getTotal_columns()
                    + current.getColumn();
            if (currentSeqNum > toReturnSeqNum) {
                break;
            }
        }
        allSeats.add(idx, toReturn);
        return new ResponseEntity<>(new ReturnResponse(toReturn), HttpStatus.OK);
    }
}