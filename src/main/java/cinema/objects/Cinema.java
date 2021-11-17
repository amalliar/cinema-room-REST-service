package cinema.objects;

import java.util.LinkedList;
import java.util.List;

public final class Cinema {

    private final int total_rows;
    private final int total_columns;
    private final List<Seat> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = _generateSeats();
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    private List<Seat> _generateSeats() {
        List<Seat> seats = new LinkedList<>();
        int total_seats = total_rows * total_columns;

        for (int i = 0; i < total_seats; ++i) {
            int row = i / total_columns + 1;
            int column = i % total_columns + 1;
            int price = (row <= 4) ? 10 : 8;

            seats.add(new Seat(row, column, price));
        }
        return seats;
    }
}