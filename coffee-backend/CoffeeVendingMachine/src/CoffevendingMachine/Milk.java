package CoffevendingMachine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Data class to store milk order details
class MilkDetails {
    String flavor;
    String temperature;
    String sweetener;
    boolean lactoseFree;
    int cost;
    String orderId;
    String date;  // ✅ Use String for compatibility with DB
    String time;

    public MilkDetails(String flavor, String temperature, String sweetener, boolean lactoseFree, int cost, String orderId, String date, String time) {
        this.flavor = flavor;
        this.temperature = temperature;
        this.sweetener = sweetener;
        this.lactoseFree = lactoseFree;
        this.cost = cost;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
    }
}

// Class for milk operations
public class Milk {
    Scanner scanner = new Scanner(System.in);

    public MilkDetails getMilkDetails() {
        System.out.println("You chose Milk.");

        List<String> validFlavors = Arrays.asList("Chocolate", "Strawberry", "Plain");
        List<String> validTemperatures = Arrays.asList("Hot", "Cold");
        List<String> validSweeteners = Arrays.asList("Sugar", "Honey", "Jaggery", "Artificial");

        String flavor;
        while (true) {
            System.out.print("Enter milk flavor (Chocolate / Strawberry / Plain): ");
            flavor = capitalize(scanner.nextLine());
            if (validFlavors.contains(flavor)) break;
            System.out.println("Invalid flavor. Please enter a valid option.");
        }

        String temperature;
        while (true) {
            System.out.print("Enter temperature (Hot / Cold): ");
            temperature = capitalize(scanner.nextLine());
            if (validTemperatures.contains(temperature)) break;
            System.out.println("Invalid temperature. Please enter Hot or Cold.");
        }

        String sweetener = "None";
        System.out.print("Do you want sweetener? (Yes / No): ");
        String sweetInput = scanner.nextLine();
        if (sweetInput.equalsIgnoreCase("Yes")) {
            while (true) {
                System.out.print("Choose sweetener (Sugar / Honey / Jaggery / Artificial): ");
                sweetener = capitalize(scanner.nextLine());
                if (validSweeteners.contains(sweetener)) break;
                System.out.println("Invalid sweetener. Please choose from the list.");
            }
        }

        boolean lactoseFree = false;
        while (true) {
            System.out.print("Do you want Lactose-Free milk? (Yes / No): ");
            String lactoseInput = scanner.nextLine();
            if (lactoseInput.equalsIgnoreCase("Yes")) {
                lactoseFree = true;
                break;
            } else if (lactoseInput.equalsIgnoreCase("No")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter Yes or No.");
            }
        }

        String orderId = "MILK" + System.currentTimeMillis();

        int cost = 30;
        if (!flavor.equalsIgnoreCase("Plain")) cost += 10;
        if (lactoseFree) cost += 5;

        // ⏰ Capture current date and time
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Summary
        System.out.println("\n--- Milk Order Summary ---");
        System.out.println("Order ID      : " + orderId);
        System.out.println("Flavor        : " + flavor);
        System.out.println("Temperature   : " + temperature);
        System.out.println("Sweetener     : " + sweetener);
        System.out.println("Lactose-Free  : " + (lactoseFree ? "Yes" : "No"));
        System.out.println("Total Cost    : ₹" + cost);
        System.out.println("Date          : " + date);
        System.out.println("Time          : " + time);
        System.out.println();

        return new MilkDetails(flavor, temperature, sweetener, lactoseFree, cost, orderId, date, time);
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return "";
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
