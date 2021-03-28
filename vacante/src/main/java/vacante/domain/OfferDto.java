package vacante.domain;

import java.util.Date;

public class OfferDto {
    private String hotelName;
    private String locationName;
    private Date startDate;
    private Date endDate;

    public OfferDto(String hotelName, String locationName, Date staretDate, Date endDate) {
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.startDate = staretDate;
        this.endDate = endDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date staretDate) {
        this.startDate = staretDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
