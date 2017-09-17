package kz.epam.waterdelivery.entity;

public class Water {
    private int waterId;
    private int waterTypeId;
    private int pricePerLiter;

    public int getWaterId() {
        return waterId;
    }

    public void setWaterId(int waterId) {
        this.waterId = waterId;
    }

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
                "waterId=" + waterId +
                ", waterTypeId='" + waterTypeId + '\'' +
                ", pricePerLiter=" + pricePerLiter +
                '}';
    }
}
