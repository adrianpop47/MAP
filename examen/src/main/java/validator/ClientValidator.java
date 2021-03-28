package validator;

import domain.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidationException {
        if (entity.getId() == null)
            throw new ValidationException("id null");
        if (entity.getName() == null)
            throw new ValidationException("name null");
        if (entity.getUsername() == null)
            throw new ValidationException("username null");
    }
}
