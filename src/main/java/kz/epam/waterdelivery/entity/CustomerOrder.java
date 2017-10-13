package kz.epam.waterdelivery.entity;

public class CustomerOrder extends Entity {
    private int customerId;
    private double amount;
    private String address;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
                ", amount=" + amount +
                ", address='" + address + '\'' +
                '}';
    }
}
