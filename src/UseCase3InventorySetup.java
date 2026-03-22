import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        single.displayDetails(inventory.getAvailability("Single Room"));
        dbl.displayDetails(inventory.getAvailability("Double Room"));
        suite.displayDetails(inventory.getAvailability("Suite Room"));
    }
}
