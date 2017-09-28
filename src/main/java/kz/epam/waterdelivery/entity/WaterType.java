package kz.epam.waterdelivery.entity;

public class WaterType extends Entity {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WaterType{" +
                "type='" + type + '\'' +
                '}';
    }
}
