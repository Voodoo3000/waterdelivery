package kz.epam.waterdelivery.entity;

public class WaterType {
    private int waterTypeId;
    private String type;

    public int getWaterTypeId() {
        return waterTypeId;
    }

    public void setWaterTypeId(int waterTypeId) {
        this.waterTypeId = waterTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WaterType{" +
                "waterTypeId=" + waterTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}
