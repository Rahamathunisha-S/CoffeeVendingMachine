package CoffevendingMachine;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetSocketAddress;

public class APIServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/saveOrder", new SaveOrderHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("✅ Server running at http://localhost:8080");
    }
}

class SaveOrderHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"))) {
                StringBuilder jsonBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }

                String json = jsonBuilder.toString();
                System.out.println("📥 Received JSON: " + json);

                // Parse JSON
                Gson gson = new Gson();
                OrderDetails order = gson.fromJson(json, OrderDetails.class);

                // Save to DB
                new OrderService().saveOrder(
                    order.getOrderId(),
                    order.getDrinkType(),
                    order.getFlavor(),
                    order.getTemperature(),
                    order.getSweetener(),
                    order.isLactoseFree(),
                    order.getCost(),
                    order.getDate(),
                    order.getTime()
                );

                String response = "Order saved!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String error = "Error saving order: " + e.getMessage();
                exchange.sendResponseHeaders(500, error.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
