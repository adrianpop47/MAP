package socialnetwork.domain;

import java.time.LocalDateTime;

public class CererePrietenie extends Entity<Long>{

    LocalDateTime date;
    Long u1;
    Long u2;
    String status;

    public CererePrietenie(Long u1, Long u2, LocalDateTime date,String status) {
        this.date = date;
        this.u1 = u1;
        this.u2 = u2;
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getU1() {
        return u1;
    }

    public Long getU2() {
        return u2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CererePrietenie{" +
                "id=" + this.getID() +
                ", date=" + date +
                ", u1=" + u1 +
                ", u2=" + u2 +
                ", status='" + status + '\'' +
                '}';
    }
}
