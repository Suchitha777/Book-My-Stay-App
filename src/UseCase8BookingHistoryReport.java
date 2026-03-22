import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 *
 * Maintains a history of confirmed bookings
 * and generates reports from stored data.
 *
 * @author Suchitha
 * @version 8.0
 */

// Booking History (Storage)
class BookingHistory {

    private List<Reservation> history;

    BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed booking
    void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    List<Reservation> getBookings() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    void generateReport(List<Reservation> bookings) {

        System.out.println("Booking History Report");

        for (Reservation r : bookings) {
            System.out.println(
                    "Guest: " + r.guestName +
                            ", Room Type: " + r.roomType
            );
        }
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addBooking(new Reservation("Abhi", "Single"));
        history.addBooking(new Reservation("Subha", "Double"));
        history.addBooking(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getBookings());
    }
}