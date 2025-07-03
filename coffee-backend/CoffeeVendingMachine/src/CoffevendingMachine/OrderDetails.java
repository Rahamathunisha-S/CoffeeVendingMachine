package CoffevendingMachine;

public class OrderDetails {
    private String orderId;
    private String drinkType;
    private String flavor;
    private String temperature;
    private String sweetener;
    private boolean lactoseFree;
    private int cost;
    private String date;
    private String time;

    // Getters
    public String getOrderId() { return orderId; }
    public String getDrinkType() { return drinkType; }
    public String getFlavor() { return flavor; }
    public String getTemperature() { return temperature; }
    public String getSweetener() { return sweetener; }
    public boolean isLactoseFree() { return lactoseFree; }
    public int getCost() { return cost; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}
