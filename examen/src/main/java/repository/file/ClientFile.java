package repository.file;

import domain.Client;

import java.text.ParseException;
import java.util.List;

public class ClientFile extends AbstractFileRepository<String, Client>{
    public ClientFile(String fileName) {
        super(fileName);
    }

    @Override
    public Client extractEntity(List<String> attributes) throws ParseException {
        Client client = new Client(attributes.get(0),attributes.get(1));
        client.setId(attributes.get(0));
        return client;
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId() + ";" + entity.getName();
    }
}
