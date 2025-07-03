package CoffevendingMachine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();

        try {
            System.out.println("Welcome to this cafe!");
            System.out.println("Please choose one of the following:");
            System.out.println("1. Milk");
            System.out.println("2. Tea");
            System.out.println("3. Coffee");
            System.out.println("4. Other");

            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Get current date and time
            String date = LocalDate.now().toString();
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            switch (choice) {
                case 1:
                    Milk milk = new Milk();
                    MilkDetails milkDetails = milk.getMilkDetails();
                    milkDetails.date = date;
                    milkDetails.time = time;

                    System.out.println("\n--- Milk Order Summary ---");
                    System.out.println("Order ID          : " + milkDetails.orderId);
                    System.out.println("Flavor            : " + milkDetails.flavor);
                    System.out.println("Temperature       : " + milkDetails.temperature);
                    System.out.println("Sweetener         : " + milkDetails.sweetener);
                    System.out.println("Lactose-Free      : " + (milkDetails.lactoseFree ? "Yes" : "No"));
                    System.out.println("Total Cost        : ₹" + milkDetails.cost);
                    System.out.println("Date              : " + milkDetails.date);
                    System.out.println("Time              : " + milkDetails.time);
                    System.out.println("Enjoy your Milk!");

                    orderService.saveOrder(
                            milkDetails.orderId, "Milk", milkDetails.flavor, milkDetails.temperature,
                            milkDetails.sweetener, milkDetails.lactoseFree, milkDetails.cost,
                            milkDetails.date, milkDetails.time);
                    System.out.println("------------------------------");
                    break;

                case 2:
                    Tea tea = new Tea();
                    TeaDetails teaDetails = tea.getTeaDetails();
                    teaDetails.date = date;
                    teaDetails.time = time;

                    System.out.println("\n--- Tea Order Summary ---");
                    System.out.println("Order ID          : " + teaDetails.orderId);
                    System.out.println("Flavor            : " + teaDetails.flavor);
                    System.out.println("Temperature       : " + teaDetails.temperature);
                    System.out.println("Sweetener         : " + teaDetails.sweetener);
                    System.out.println("Lactose-Free      : " + (teaDetails.lactoseFree ? "Yes" : "No"));
                    System.out.println("Total Cost        : ₹" + teaDetails.cost);
                    System.out.println("Date              : " + teaDetails.date);
                    System.out.println("Time              : " + teaDetails.time);
                    System.out.println("Enjoy your Tea!");

                    orderService.saveOrder(
                            teaDetails.orderId, "Tea", teaDetails.flavor, teaDetails.temperature,
                            teaDetails.sweetener, teaDetails.lactoseFree, teaDetails.cost,
                            teaDetails.date, teaDetails.time);
                    System.out.println("------------------------------");
                    break;

                case 3:
                    Coffee coffee = new Coffee();
                    CoffeeDetails coffeeDetails = coffee.getCoffeeDetails();
                    coffeeDetails.date = date;
                    coffeeDetails.time = time;

                    System.out.println("\n--- Coffee Order Summary ---");
                    System.out.println("Order ID          : " + coffeeDetails.orderId);
                    System.out.println("Flavor            : " + coffeeDetails.type);
                    System.out.println("Temperature       : " + coffeeDetails.temperature);
                    System.out.println("Sweetener         : " + coffeeDetails.sweetener);
                    System.out.println("Lactose-Free      : " + (coffeeDetails.lactoseFree ? "Yes" : "No"));
                    System.out.println("Total Cost        : ₹" + coffeeDetails.cost);
                    System.out.println("Date              : " + coffeeDetails.date);
                    System.out.println("Time              : " + coffeeDetails.time);
                    System.out.println("Enjoy your Coffee!");

                    orderService.saveOrder(
                            coffeeDetails.orderId, "Coffee", coffeeDetails.type, coffeeDetails.temperature,
                            coffeeDetails.sweetener, coffeeDetails.lactoseFree, coffeeDetails.cost,
                            coffeeDetails.date, coffeeDetails.time);
                    System.out.println("------------------------------");
                    break;

                case 4:
                    Others others = new Others();
                    OtherDetails otherDetails = others.getOtherDetails();
                    otherDetails.date = date;
                    otherDetails.time = time;

                    System.out.println("\n--- Other Drink Order Summary ---");
                    System.out.println("Order ID          : " + otherDetails.orderId);
                    System.out.println("Flavor            : " + otherDetails.flavor);
                    System.out.println("Temperature       : " + otherDetails.temperature);
                    System.out.println("Sweetener         : " + otherDetails.sweetener);
                    System.out.println("Lactose-Free      : " + (otherDetails.lactoseFree ? "Yes" : "No"));
                    System.out.println("Total Cost        : ₹" + otherDetails.cost);
                    System.out.println("Date              : " + otherDetails.date);
                    System.out.println("Time              : " + otherDetails.time);
                    System.out.println("Enjoy your Drink!");

                    orderService.saveOrder(
                            otherDetails.orderId, "Others", otherDetails.flavor, otherDetails.temperature,
                            otherDetails.sweetener, otherDetails.lactoseFree, otherDetails.cost,
                            otherDetails.date, otherDetails.time);
                    System.out.println("------------------------------");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number (1-4).");
        } finally {
            scanner.close();
        }
    }
}
