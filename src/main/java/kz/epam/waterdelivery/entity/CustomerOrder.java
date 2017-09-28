package kz.epam.waterdelivery.entity;

public class CustomerOrder extends Entity {
    private int customerId;
    private int orderContentId;
    private int amount;
    private String address;

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
                "customerId=" + customerId +
                ", orderContentId=" + orderContentId +
                ", amount=" + amount +
                ", address='" + address + '\'' +
                '}';
    }
}
