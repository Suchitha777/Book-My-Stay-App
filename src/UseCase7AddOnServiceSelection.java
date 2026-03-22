import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 *
 * This class demonstrates how optional services
 * can be attached to a confirmed booking.
 *
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @author Suchitha
 * @version 7.0
 */

// Add-On Service class
class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

// Service Manager
class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap;

    AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Calculate total cost
    double calculateTotalCost(String reservationId) {
        double total = 0;

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }

        return total;
    }
}

// Main class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Example reservation ID (from UC6)
        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("WiFi", 300));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 700));

        // Calculate total
        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
