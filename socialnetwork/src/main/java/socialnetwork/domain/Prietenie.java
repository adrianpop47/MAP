package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Prietenie extends Entity<Long>  {

    LocalDateTime date;
    Long u1;
    Long u2;

    public Prietenie(LocalDateTime date, Long u1, Long u2) {
        this.date = date;
        this.u1 = u1;
        this.u2 = u2;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getU1() {
        return u1;
    }

    public void setU1(Long u1) {
        this.u1 = u1;
    }

    public Long getU2() {
        return u2;
    }

    public void setU2(Long u2) {
        this.u2 = u2;
    }

    @Override
    public String toString() {
        return "Prietenie{" +
                "id=" + this.getID() +
                ", date=" + date +
                ", u1=" + u1 +
                ", u2=" + u2 +
                '}';
    }
}
