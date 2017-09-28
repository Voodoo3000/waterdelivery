package kz.epam.waterdelivery.entity;

public class Water extends Entity {
    private int waterTypeId;
    private int pricePerLiter;

    public int getWaterTypeId() {
        return waterTypeId;
    }

    public void setWaterTypeId(int waterTypeId) {
        this.waterTypeId = waterTypeId;
    }

    public int getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(int pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    @Override
    public String toString() {
        return "Water{" +
                "waterTypeId=" + waterTypeId +
                ", pricePerLiter=" + pricePerLiter +
                '}';
    }
}
