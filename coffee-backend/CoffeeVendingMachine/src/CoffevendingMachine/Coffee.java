package CoffevendingMachine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Data class to store Coffee order details
class CoffeeDetails {
    String type;
    String temperature;
    String sweetener;
    boolean lactoseFree;
    int cost;
    String orderId;
    String date;  // changed from double to String
    String time;

    public CoffeeDetails(String type, String temperature, String sweetener, boolean lactoseFree, int cost, String orderId, String date, String time) {
        this.type = type;
        this.temperature = temperature;
        this.sweetener = sweetener;
        this.lactoseFree = lactoseFree;
        this.cost = cost;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
    }
}

// Class for coffee operations
public class Coffee {
    Scanner scanner = new Scanner(System.in);

    public CoffeeDetails getCoffeeDetails() {
        System.out.println("You chose Coffee.");

        List<String> validTypes = Arrays.asList("Espresso", "Latte", "Cappuccino", "Mocha", "Black");
        List<String> validTemperatures = Arrays.asList("Hot", "Cold");
        List<String> validSweeteners = Arrays.asList("Sugar", "Honey", "Jaggery", "Artificial");

        String type;
        while (true) {
            System.out.print("Enter coffee type (Espresso / Latte / Cappuccino / Mocha / Black): ");
            type = capitalize(scanner.nextLine());
            if (validTypes.contains(type)) break;
            System.out.println("Invalid coffee type. Please choose a valid option.");
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
            System.out.print("Do you want Lactose-Free milk in coffee? (Yes / No): ");
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

        String orderId = "COF" + System.currentTimeMillis();

        int cost = 40;
        if (!type.equalsIgnoreCase("Black")) cost += 10;
        if (lactoseFree) cost += 5;

        // ⏰ Get current date and time
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Summary
        System.out.println("\n--- Coffee Order Summary ---");
        System.out.println("Order ID      : " + orderId);
        System.out.println("Type          : " + type);
        System.out.println("Temperature   : " + temperature);
        System.out.println("Sweetener     : " + sweetener);
        System.out.println("Lactose-Free  : " + (lactoseFree ? "Yes" : "No"));
        System.out.println("Total Cost    : ₹" + cost);
        System.out.println("Date          : " + date);
        System.out.println("Time          : " + time);
        System.out.println();

        return new CoffeeDetails(type, temperature, sweetener, lactoseFree, cost, orderId, date, time);
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return "";
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
