package domain;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket extends Entity<Long> {
    private Long ticketId;
    private String username;
    private Long flightId;
    private LocalDateTime purchaseTime;

    public Ticket(Long ticketId, String username, Long flightId, LocalDateTime purchaseTime) {
        super.setId(ticketId);
        this.ticketId = ticketId;
        this.username = username;
        this.flightId = flightId;
        this.purchaseTime = purchaseTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", username='" + username + '\'' +
                ", flightId=" + flightId +
                ", purchaseTime=" + purchaseTime +
                '}';
    }
}
