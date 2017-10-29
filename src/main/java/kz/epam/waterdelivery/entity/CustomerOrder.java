package kz.epam.waterdelivery.entity;

import java.util.Date;

public class CustomerOrder extends Entity {
    private int customerId;
    private double amount;
    private boolean payment;
    private Date orderDate;
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

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public java.sql.Date getCurrentDate() {
        java.util.Date orderDate = new java.util.Date();
        return new java.sql.Date(orderDate.getTime());
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
                ", payment=" + payment +
                ", orderDate=" + orderDate +
                ", address='" + address + '\'' +
                '}';
    }
}
