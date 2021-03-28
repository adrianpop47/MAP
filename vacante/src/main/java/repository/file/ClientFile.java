package repository.file;

import vacante.domain.Client;
import vacante.domain.Hobby;
import vacante.domain.Hotel;
import vacante.domain.Type;

import java.text.ParseException;
import java.util.List;

public class ClientFile extends AbstractFileRepository<Long, Client>{
    public ClientFile(String fileName) {
        super(fileName);
    }

    @Override
    public Client extractEntity(List<String> attributes) throws ParseException {
        Client client = new Client(Long.parseLong(attributes.get(0)), attributes.get(1),
                Integer.parseInt(attributes.get(2)), Integer.parseInt(attributes.get(3)),
                Hobby.valueOf(attributes.get(4)));
        return client;
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId() + ";" + entity.getName() + ";" + entity.getFidelityGrade() + ";"
                + entity.getVarsta() + ";" + entity.getHobby();
    }
}
