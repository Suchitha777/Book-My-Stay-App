import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Handles booking confirmation, unique room assignment,
 * and inventory update while preventing double booking.
 *
 * @author Suchitha
 * @version 6.0
 */

// Booking Service (NEW)
class RoomAllocationService {

    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;

    RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
    }

    void processBookings(Queue<Reservation> queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String roomType = r.roomType;

            int available = inventory.getAvailability(roomType + " Room");

            if (available > 0) {

                // Initialize set if not exists
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());

                Set<String> assigned = allocatedRooms.get(roomType);

                // Generate unique room ID
                String roomId = roomType + "-" + (assigned.size() + 1);

                // Ensure uniqueness
                if (!assigned.contains(roomId)) {
                    assigned.add(roomId);

                    // Update inventory
                    inventory.updateAvailability(roomType + " Room", available - 1);

                    System.out.println(
                            "Booking confirmed for Guest: " + r.guestName +
                                    ", Room ID: " + roomId
                    );
                }
            }
        }
    }
}

// Main class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        // Inventory (from UC3)
        RoomInventory inventory = new RoomInventory();

        // Queue (from UC5)
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Abhi", "Single"));
        queue.add(new Reservation("Subha", "Single"));
        queue.add(new Reservation("Vanmathi", "Suite"));

        // Allocation service
        RoomAllocationService service = new RoomAllocationService(inventory);

        // Process bookings
        service.processBookings(queue);
    }
}
