package socialnetwork.repository.file;

import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.service.UtilizatorService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MesajFile extends AbstractFileRepository<Long, Message>{

    public MesajFile(String fileName) {
        super(fileName);
    }

    @Override
    public Message extractEntity(List<String> attributes){
        List<Long> to = new ArrayList<>();
        Integer length = Integer.parseInt(attributes.get(2));
        for(int i = 0; i < length; i++)
            to.add(Long.parseLong(attributes.get(i + 3)));

        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(attributes.get(length + 4), formatter);

        Message message = new Message(Long.parseLong(attributes.get(1)), to, attributes.get(length + 3), dateTime);
        Long replyId = Long.parseLong(attributes.get(length + 5));
        if(replyId != -1)
            message.setReply(this.findOne(replyId));
        message.setId(Long.parseLong(attributes.get(0)));
        return message;
    }

    @Override
    protected String createEntityAsString(Message entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = entity.getDate();
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"

        String to = "";
        for(Long i : entity.getTo())
            to = to + i + ";";
        if(entity.getReply() != null)
            return entity.getID()+";"+entity.getFrom()+";"+entity.getTo().size()+";"+to+entity.getMessage()+";"+formattedDateTime+";"+entity.getReply().getID();
        else
            return entity.getID()+";"+entity.getFrom()+";"+entity.getTo().size()+";"+to+entity.getMessage()+";"+formattedDateTime+";"+"-1";
    }
}
