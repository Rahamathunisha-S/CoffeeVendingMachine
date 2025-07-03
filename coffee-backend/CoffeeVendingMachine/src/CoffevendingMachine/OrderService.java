package CoffevendingMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {
    public void saveOrder(String orderId, String drinkType, String flavor, String temperature,
                          String sweetener, boolean lactoseFree, int cost, String date, String time) {

        String sql = "INSERT INTO orders (order_id, drink_type, flavor, temperature, sweetener, lactose_free, cost, date, time) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("📌 Connecting to database...");
            System.out.println("📝 Inserting Order: " + orderId + ", " + drinkType + ", " + flavor + ", " + temperature + ", " +
                    sweetener + ", " + lactoseFree + ", ₹" + cost + ", " + date + ", " + time);

            stmt.setString(1, orderId);
            stmt.setString(2, drinkType);
            stmt.setString(3, flavor);
            stmt.setString(4, temperature);
            stmt.setString(5, sweetener);
            stmt.setBoolean(6, lactoseFree);
            stmt.setInt(7, cost);
            stmt.setString(8, date);
            stmt.setString(9, time);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Order saved to database!");
            } else {
                System.out.println("⚠️ Order not saved (0 rows affected).");
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("❌ Other Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
