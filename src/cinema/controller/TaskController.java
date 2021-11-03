package cinema.controller;

import cinema.objects.Cinema;
import cinema.requests.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TaskController {

    Cinema cinema = new Cinema(9, 9);
    UserService service = new UserService(cinema);

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        return service.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchaseTicket(
            @RequestBody PurchaseRequest request) {
        return service.purchaseTicket(request);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnTicket(
            @RequestBody ReturnRequest request) {
        return service.returnTicket(request);
    }

    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> RequestExceptionHandler(
            RequestException ex) {
        return Map.of("error", ex.getMessage());
    }
}