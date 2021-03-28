package socialnetwork.domain;

import java.time.LocalDateTime;


public class Prietenie extends Entity<Tuple<Long,Long>> {

    LocalDateTime date;
    Tuple<Utilizator, Utilizator> tuple;

    public Prietenie(Utilizator u1, Utilizator u2) {
        this.tuple = new Tuple<>(u1, u2);
    }

    /**
     *
     * @return the date when the friendship was created
     */
    public LocalDateTime getDate() {
        return date;
    }
}
