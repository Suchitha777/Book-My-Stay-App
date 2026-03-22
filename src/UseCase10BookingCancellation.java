import java.util.*;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Demonstrates safe cancellation with inventory restoration
 * using Stack (LIFO).
 *
 * @author Suchitha
 * @version 10.0
 */

// Cancellation Service
class BookingCancellationService {

    private RoomInventory inventory;
    private Map<String, String> reservationMap; // reservationId → roomType
    private Stack<String> rollbackStack;

    BookingCancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.reservationMap = new HashMap<>();
        this.rollbackStack = new Stack<>();
    }

    // Simulate existing booking (from UC6)
    void addReservation(String reservationId, String roomType) {
        reservationMap.put(reservationId, roomType);
    }

    // Cancel booking
    void cancelBooking(String reservationId) {

        if (!reservationMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation. Cannot cancel.");
            return;
        }

        String roomType = reservationMap.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        int current = inventory.getAvailability(roomType + " Room");
        inventory.updateAvailability(roomType + " Room", current + 1);

        // Remove booking
        reservationMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);

        // Show rollback history
        System.out.println("\nRollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }

        // Show updated inventory
        int updated = inventory.getAvailability(roomType + " Room");
        System.out.println("\nUpdated " + roomType + " Room Availability: " + updated);
    }
}

// Main class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();

        BookingCancellationService service = new BookingCancellationService(inventory);

        // Simulate previous booking (UC6)
        service.addReservation("Single-1", "Single");

        // Cancel booking
        service.cancelBooking("Single-1");
    }
}