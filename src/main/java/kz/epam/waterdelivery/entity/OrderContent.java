package kz.epam.waterdelivery.entity;

public class OrderContent extends Entity {
    private int waterId;
    private int bottleSizeId;
    private int quantity;
    private double price;
    private int customerOrderId;

    public int getWaterId() {
        return waterId;
    }

    public void setWaterId(int waterId) {
        this.waterId = waterId;
    }

    public int getBottleSizeId() {
        return bottleSizeId;
    }

    public void setBottleSizeId(int bottleSizeId) {
        this.bottleSizeId = bottleSizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    @Override
    public String toString() {
        return "OrderContent{" +
                "waterId=" + waterId +
                ", bottleSizeId=" + bottleSizeId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", customerOrderId=" + customerOrderId +
                '}';
    }
}
