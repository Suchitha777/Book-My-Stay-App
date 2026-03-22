/**
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only access to inventory.
 *
 * @author Suchitha
 * @version 4.0
 */

class RoomSearchService {

    private RoomInventory inventory;

    RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void displayAvailableRooms(Room[] rooms) {

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                System.out.println(room.getRoomType() + ":");
                System.out.println("Beds: " + room.beds);
                System.out.println("Size: " + room.size + " sqft");
                System.out.println("Price per night: " + room.price);
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("Room Search\n");

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = { single, dbl, suite };

        RoomInventory inventory = new RoomInventory();

        RoomSearchService search = new RoomSearchService(inventory);

        search.displayAvailableRooms(rooms);
    }
}