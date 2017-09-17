package kz.epam.waterdelivery.entity;

public class CustomerOrder {
    private int orderId;
    private int customerId;
    private int orderContentId;
    private int amount;
    private String address;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(int orderContentId) {
        this.orderContentId = orderContentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", orderContentId=" + orderContentId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", address='" + address + '\'' +
                '}';
    }
}
