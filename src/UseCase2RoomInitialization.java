/**
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This use case introduces object-oriented design using
 * abstraction, inheritance, and encapsulation.
 *
 * @author Suchitha
 * @version 2.0
 */

// Abstract class
abstract class Room {
    int beds;
    int size;
    double price;

    // Constructor
    Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Abstract method
    abstract String getRoomType();

    // Display common details
    void displayDetails(int available) {
        System.out.println(getRoomType() + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

// Single Room
class SingleRoom extends Room {
    SingleRoom() {
        super(1, 250, 1500.0);
    }

    String getRoomType() {
        return "Single Room";
    }
}

// Double Room
class DoubleRoom extends Room {
    DoubleRoom() {
        super(2, 400, 2500.0);
    }

    String getRoomType() {
        return "Double Room";
    }
}

// Suite Room
class SuiteRoom extends Room {
    SuiteRoom() {
        super(3, 750, 5000.0);
    }

    String getRoomType() {
        return "Suite Room";
    }
}

// Main class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Room objects
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        single.displayDetails(singleAvailable);
        dbl.displayDetails(doubleAvailable);
        suite.displayDetails(suiteAvailable);
    }
}
