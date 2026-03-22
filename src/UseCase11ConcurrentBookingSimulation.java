import java.util.*;

/**
 * Use Case 11: Concurrent Booking Simulation
 *
 * Demonstrates thread-safe booking using synchronization.
 *
 * @author Suchitha
 * @version 11.0
 */

// Thread Processor
class ConcurrentBookingProcessor implements Runnable {

    private Queue<Reservation> queue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    ConcurrentBookingProcessor(Queue<Reservation> queue,
                               RoomInventory inventory,
                               RoomAllocationService allocationService) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // 🔒 CRITICAL SECTION (Queue access)
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                r = queue.poll();
            }

            // 🔒 CRITICAL SECTION (Allocation + Inventory)
            synchronized (allocationService) {
                Queue<Reservation> temp = new LinkedList<>();
                temp.add(r);
                allocationService.processBookings(temp);
            }
        }
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        // Shared resources
        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Double"));
        bookingQueue.add(new Reservation("Kural", "Suite"));
        bookingQueue.add(new Reservation("Subha", "Single"));

        RoomAllocationService allocationService =
                new RoomAllocationService(inventory);

        // Threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Final inventory
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getAvailability("Single Room"));
        System.out.println("Double: " + inventory.getAvailability("Double Room"));
        System.out.println("Suite: " + inventory.getAvailability("Suite Room"));
    }
}
