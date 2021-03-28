package socialnetwork.repository.file;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PrietenFile extends AbstractFileRepository<Long, Prietenie>{

    public PrietenFile(String fileName) {
        super(fileName);
    }

    @Override
    public Prietenie extractEntity(List<String> attributes){
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(attributes.get(1), formatter);

        Prietenie user = new Prietenie(dateTime,Long.parseLong(attributes.get(2)), Long.parseLong(attributes.get(3)));
        user.setId(Long.parseLong(attributes.get(0)));
        return user;
    }

    @Override
    protected String createEntityAsString(Prietenie entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = entity.getDate();
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"

        return entity.getID()+";"+formattedDateTime+";"+entity.getU1()+";"+entity.getU2();
    }
}
