package vacante.domain;

import java.time.LocalDateTime;

public class Reservation extends Entity<Double>{
    private Double reservationId;
    private Double hotelId;
    private Long clientId;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Double reservationId, Double hotelId, Long clientId, LocalDateTime startDate, Integer noNights) {
        super.setId(reservationId);
        this.reservationId = reservationId;
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Double getReservationId() {
        return reservationId;
    }

    public void setReservationId(Double reservationId) {
        this.reservationId = reservationId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", hotelId=" + hotelId +
                ", clientId=" + clientId +
                ", startDate=" + startDate +
                ", noNights=" + noNights +
                '}';
    }
}
