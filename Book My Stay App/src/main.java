import java.util.*;

// ============================
// MAIN APPLICATION
// ============================
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BOOK MY STAY - BASE SYSTEM   ");
        System.out.println("=================================");

        // Initialize system
        HotelManagementSystem system = new HotelManagementSystem();

        // Load initial data
        system.initializeRooms();

        // Display current system state
        system.displayRooms();

        System.out.println("\nSystem initialized successfully.");
    }
}


// ============================
// HOTEL MANAGEMENT SYSTEM
// ============================
class HotelManagementSystem {

    // Map → Room Number → Room Object
    private Map<Integer, Room> roomInventory;

    // Queue → Booking Requests (FIFO)
    private Queue<BookingRequest> bookingQueue;

    // Set → Unique Customers (avoid duplicates)
    private Set<String> customerSet;

    public HotelManagementSystem() {
        roomInventory = new HashMap<>();
        bookingQueue = new LinkedList<>();
        customerSet = new HashSet<>();
    }

    // Initialize rooms (Hardcoded for now)
    public void initializeRooms() {
        roomInventory.put(101, new Room(101, "Single", true));
        roomInventory.put(102, new Room(102, "Double", true));
        roomInventory.put(103, new Room(103, "Suite", true));
    }

    // Display all rooms
    public void displayRooms() {
        System.out.println("\nAvailable Rooms:");

        for (Room room : roomInventory.values()) {
            System.out.println(room);
        }
    }
}


// ============================
// ROOM CLASS
// ============================
class Room {
    private int roomNumber;
    private String type;
    private boolean isAvailable;

    public Room(int roomNumber, String type, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean status) {
        this.isAvailable = status;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + type + " | Available: " + isAvailable;
    }
}


// ============================
// BOOKING REQUEST CLASS
// ============================
class BookingRequest {
    private String customerName;
    private String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }
}