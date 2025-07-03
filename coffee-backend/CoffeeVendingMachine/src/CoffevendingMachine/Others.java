package CoffevendingMachine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Data class to store Other drink details
class OtherDetails {
    String flavor;
    String temperature;
    String sweetener;
    boolean lactoseFree;
    int cost;
    String orderId;
    String date;  // Changed to String
    String time;  // Changed to String

    public OtherDetails(String flavor, String temperature, String sweetener, boolean lactoseFree, int cost, String orderId, String date, String time) {
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

// Class for handling "Other" drinks like Horlicks, Complan, Boost, Bournvita
public class Others {
    Scanner scanner = new Scanner(System.in);

    public OtherDetails getOtherDetails() {
        System.out.println("You chose Other drinks.");

        List<String> validFlavors = Arrays.asList("Horlicks", "Complan", "Boost", "Bournvita");
        List<String> validTemperatures = Arrays.asList("Hot", "Cold");
        List<String> validSweeteners = Arrays.asList("Sugar", "Honey", "Jaggery", "Artificial");

        String flavor;
        while (true) {
            System.out.print("Enter drink type (Horlicks / Complan / Boost / Bournvita): ");
            flavor = capitalize(scanner.nextLine());
            if (validFlavors.contains(flavor)) break;
            System.out.println("Invalid drink type. Please enter a valid option.");
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

        String orderId = "OTHER" + System.currentTimeMillis();

        int cost = 35;
        if (!flavor.equalsIgnoreCase("Horlicks")) cost += 5;
        if (lactoseFree) cost += 5;

        // ⏰ Date & Time
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Summary
        System.out.println("\n--- Other Drink Order Summary ---");
        System.out.println("Order ID      : " + orderId);
        System.out.println("Type          : " + flavor);
        System.out.println("Temperature   : " + temperature);
        System.out.println("Sweetener     : " + sweetener);
        System.out.println("Lactose-Free  : " + (lactoseFree ? "Yes" : "No"));
        System.out.println("Total Cost    : ₹" + cost);
        System.out.println("Date          : " + date);
        System.out.println("Time          : " + time);
        System.out.println();

        return new OtherDetails(flavor, temperature, sweetener, lactoseFree, cost, orderId, date, time);
    }

    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return "";
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
