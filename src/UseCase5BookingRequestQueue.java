import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * Demonstrates fair handling of booking requests
 * using Queue data structure.
 *
 * @author Suchitha
 * @version 5.0
 */

// Reservation class (NEW — safe to create)
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Booking Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    // Process requests (FIFO)
    void processRequests() {

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            System.out.println(
                    "Processing booking for Guest: " + r.guestName +
                            ", Room Type: " + r.roomType
            );
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add requests (arrival order)
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process requests
        bookingQueue.processRequests();
    }
}
