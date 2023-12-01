package vn.edu.iuh.fit.response;

public class ResponseProduct {
    private String name;
    private String path;
    private double prices;
    private int quantity;
    private String orderDate;

    public ResponseProduct() {
    }

    public ResponseProduct(String name, String path, double prices, int quantity, String orderDate) {
        this.name = name;
        this.path = path;
        this.prices = prices;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
