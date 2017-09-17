package kz.epam.waterdelivery.entity;

public class OrderContent {
    private int orderContentId;
    private int waterId;
    private int bottleSize;
    private int quantity;

    public int getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(int orderContentId) {
        this.orderContentId = orderContentId;
    }

    public int getWaterId() {
        return waterId;
    }

    public void setWaterId(int waterId) {
        this.waterId = waterId;
    }

    public int getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(int bottleSize) {
        this.bottleSize = bottleSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderContent{" +
                "orderContentId=" + orderContentId +
                ", waterId=" + waterId +
                ", bottleSize=" + bottleSize +
                ", quantity=" + quantity +
                '}';
    }
}
