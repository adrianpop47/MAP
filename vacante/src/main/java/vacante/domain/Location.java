package vacante.domain;

public class Location extends Entity<Double> {
    private Double locationId;
    private String locationName;

    public Location(Double locationId, String locationName) {
        super.setId(locationId);
        this.locationName = locationName;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return this.getId().intValue() + "." + locationName;
    }
}