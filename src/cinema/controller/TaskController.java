package cinema.controller;

import cinema.exception.BadRequestException;
import cinema.exception.RequestException;
import cinema.exception.UnauthorizedException;
import cinema.objects.Cinema;
import cinema.wrappers.*;
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

    @PostMapping("/stats")
    public ResponseEntity<StatsResponse> getStatistics(
            @RequestParam(required = false) String password) {
        return service.getStats(password);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> BadRequestExceptionHandler(
            RequestException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> UnauthorizedExceptionHandler(
            RequestException ex) {
        return Map.of("error", ex.getMessage());
    }
}