import java.io.*;
import java.util.*;

/**
 * Use Case 12: Data Persistence & System Recovery
 *
 * Demonstrates saving and loading inventory state
 * from a file for recovery.
 *
 * @author Suchitha
 * @version 12.0
 */

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "inventory.txt";

    // Save inventory to file
    void saveInventory(RoomInventory inventory) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            writer.write("Single=" + inventory.getAvailability("Single Room"));
            writer.newLine();
            writer.write("Double=" + inventory.getAvailability("Double Room"));
            writer.newLine();
            writer.write("Suite=" + inventory.getAvailability("Suite Room"));

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // Load inventory from file
    Map<String, Integer> loadInventory() {

        Map<String, Integer> data = new HashMap<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                data.put(parts[0], Integer.parseInt(parts[1]));
            }

            return data;

        } catch (Exception e) {
            return null; // corrupted file
        }
    }
}

// Main class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        PersistenceService persistence = new PersistenceService();

        // Try loading data
        Map<String, Integer> loadedData = persistence.loadInventory();

        RoomInventory inventory = new RoomInventory();

        if (loadedData == null) {

            System.out.println("No valid inventory data found. Starting fresh.\n");

        } else {

            System.out.println("Inventory restored from file.\n");

            inventory.updateAvailability("Single Room", loadedData.get("Single"));
            inventory.updateAvailability("Double Room", loadedData.get("Double"));
            inventory.updateAvailability("Suite Room", loadedData.get("Suite"));
        }

        // Show current inventory
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getAvailability("Single Room"));
        System.out.println("Double: " + inventory.getAvailability("Double Room"));
        System.out.println("Suite: " + inventory.getAvailability("Suite Room"));

        // Save state
        persistence.saveInventory(inventory);
    }
}
