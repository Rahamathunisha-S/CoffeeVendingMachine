package CoffevendingMachine;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Data class to store Tea order details
class TeaDetails {
    public String flavor;
    public String temperature;
    public String sweetener;
    public boolean lactoseFree;
    public int cost;
    public String orderId;
    public String date; // yyyy-MM-dd
    public String time; // HH:mm:ss

    public TeaDetails(String flavor, String temperature, String sweetener, boolean lactoseFree, int cost,
                      String orderId, String date, String time) {
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

// Class for tea operations
public class Tea {
    Scanner scanner = new Scanner(System.in);

    public TeaDetails getTeaDetails() {
        System.out.println("You chose Tea.");

        // Valid options
        List<String> validFlavors = Arrays.asList("Regular", "Masala", "Lemon", "Green", "Tulsi", "Cardamom");
        List<String> validTemperatures = Arrays.asList("Hot", "Cold");
        List<String> validSweeteners = Arrays.asList("Sugar", "Honey", "Jaggery");

        // Flavor
        String flavor;
        while (true) {
            System.out.print("Enter tea flavor (Regular / Masala / Lemon / Green / Tulsi / Cardamom): ");
            flavor = scanner.nextLine();
            flavor = capitalize(flavor);
            if (validFlavors.contains(flavor)) {
                break;
            } else {
                System.out.println("Invalid flavor. Please enter a valid option.");
            }
        }

        // Temperature
        String temperature;
        while (true) {
            System.out.print("Enter temperature (Hot / Cold): ");
            temperature = scanner.nextLine();
            temperature = capitalize(temperature);
            if (validTemperatures.contains(temperature)) {
                break;
            } else {
                System.out.println("Invalid temperature. Please enter Hot or Cold.");
            }
        }

        // Sweetener
        String sweetener = "None";
        System.out.print("Do you want sweetener? (Yes / No): ");
        String sweetInput = scanner.nextLine();
        if (sweetInput.equalsIgnoreCase("Yes")) {
            while (true) {
                System.out.print("Choose sweetener (Sugar / Honey / Jaggery): ");
                sweetener = scanner.nextLine();
                sweetener = capitalize(sweetener);
                if (validSweeteners.contains(sweetener)) {
                    break;
                } else {
                    System.out.println("Invalid sweetener. Please choose from the list.");
                }
            }
        }

        // Lactose-Free
        boolean lactoseFree = false;
        while (true) {
            System.out.print("Do you want Lactose-Free milk in tea? (Yes / No): ");
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

        // Order ID
        String orderId = "TEA" + System.currentTimeMillis();

        // Date and Time
        LocalDateTime now = LocalDateTime.now();
        String date = now.toLocalDate().toString(); // yyyy-MM-dd
        String time = now.toLocalTime().withNano(0).toString(); // HH:mm:ss

        // Cost calculation
        int cost = 20;
        if (!flavor.equalsIgnoreCase("Regular")) {
            cost += 10;
        }
        if (lactoseFree) {
            cost += 5;
        }

        // Summary
        System.out.println("\n--- Tea Order Summary ---");
        System.out.println("Order ID      : " + orderId);
        System.out.println("Flavor        : " + flavor);
        System.out.println("Temperature   : " + temperature);
        System.out.println("Sweetener     : " + sweetener);
        System.out.println("Lactose-Free  : " + (lactoseFree ? "Yes" : "No"));
        System.out.println("Total Cost    : ₹" + cost);
        System.out.println("Date          : " + date);
        System.out.println("Time          : " + time);
        System.out.println();

        return new TeaDetails(flavor, temperature, sweetener, lactoseFree, cost, orderId, date, time);
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return "";
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
