package vn.edu.iuh.fit.entity;

public class ResponseInfoProduct {
    private long productId;
    private String name;
    private String path;
    private double price;

    public ResponseInfoProduct() {
    }

    public ResponseInfoProduct(long productId, String name, String path, double price) {
        this.productId = productId;
        this.name = name;
        this.path = path;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ResponseInfoProduct{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", price=" + price +
                '}';
    }
}
