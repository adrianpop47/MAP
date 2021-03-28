package socialnetwork.service;

import socialnetwork.domain.Message;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.utils.IdProvider;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MesajService implements Observable {
    private Repository<Long, Message> repo;
    private IdProvider idProvider;

    public MesajService(Repository<Long, Message> repo) {
        this.repo = repo;
        this.idProvider = new IdProvider(this.lastId());
    }

    public Iterable<Message> getAll(){
        return repo.findAll();
    }

    private long lastId()
    {
        final long[] id = {-1};
        this.getAll().forEach(x->
        {
            if(x.getID() > id[0])
                id[0] = x.getID();
        });
        return id[0];
    }

    public List<Message> conversatieUtilizatori(long id1, long id2) {
        Predicate<Message> filtruConversatii = m -> m.getFrom() == id1 && m.getTo().contains(id2) || m.getFrom() == id2 && m.getTo().contains(id1);
        List<Message> rez = StreamSupport
                .stream(this.getAll().spliterator(), false)
                .filter(filtruConversatii)
                .sorted(Comparator.comparing(Message::getDate))
                .collect(Collectors.toList());
        return rez;
    }


    public Message addMesaj(Message message) {
        message.setId(idProvider.getUniqueId());
        Message task = repo.add(message);
        if(task != null)
            throw new IllegalArgumentException("Id deja existent!");
        notifyObservers();
        return task;
    }

    public Message replyMessage(long userId, long messageId, String message, LocalDateTime dateTime) throws IOException {
        Message initialMesage = repo.findOne(messageId);
        if(initialMesage == null)
            throw new IllegalArgumentException("Nu exista acest mesaj!");
        if(initialMesage.getReply() != null)
            throw new IllegalArgumentException("Sa raspuns deja la acest mesaj!");

        List<Long> to = new ArrayList<>();
        to.add(initialMesage.getFrom());
        Message reply = new Message(userId, to, message, dateTime);
        reply.setId(idProvider.getUniqueId());
        repo.add(reply);
        initialMesage.setReply(reply);
        repo.delete(initialMesage.getID());
        repo.add(initialMesage);
        notifyObservers();
        return reply;
    }

    public List<Message> messagesWithoutReply (long userId){
        return StreamSupport
                .stream(this.getAll().spliterator(), false)
                .filter(x -> x.getTo().contains(userId))
                .filter(x -> x.getReply() == null)
                .collect(Collectors.toList());
    }

    private List<Observer> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(x->x.update());
    }
}
