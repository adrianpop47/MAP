package socialnetwork.repository.file;

import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Message;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CereriFile  extends AbstractFileRepository<Long, CererePrietenie>{

    public CereriFile(String fileName) {
        super(fileName);
    }

    @Override
    public CererePrietenie extractEntity(List<String> attributes) throws ParseException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(attributes.get(3), formatter);

        CererePrietenie cererePrietenie = new CererePrietenie(Long.parseLong(attributes.get(1)), Long.parseLong(attributes.get(2)), dateTime, attributes.get(4));
        cererePrietenie.setId(Long.parseLong(attributes.get(0)));
        return cererePrietenie;
    }

    @Override
    protected String createEntityAsString(CererePrietenie entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = entity.getDate();
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"

        return entity.getID()+";"+entity.getU1()+";"+entity.getU2()+";"+formattedDateTime+";"+entity.getStatus();
    }
}
