package kz.epam.waterdelivery.entity;

public class BottleSize {
    private int bottleId;
    private double size;

    public int getBottleId() {
        return bottleId;
    }

    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BottleSize{" +
                "bottleId=" + bottleId +
                ", size=" + size +
                '}';
    }
}
