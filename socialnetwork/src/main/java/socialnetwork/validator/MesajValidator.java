package socialnetwork.validator;

import socialnetwork.domain.Message;
import socialnetwork.repository.Repository;
import socialnetwork.service.PrietenService;
import socialnetwork.service.UtilizatorService;

public class MesajValidator implements Validator<Message> {
    private UtilizatorService utilizatorService;

    public MesajValidator(UtilizatorService service) {
        this.utilizatorService = service;
    }

    @Override
    public void validate(Message entity) throws ValidationException {
        String msg = "";
        if(utilizatorService.findOne(entity.getFrom()) == null)
            msg += "No user with this id: " + entity.getFrom();
        if(entity.getTo().size() <= 0)
            msg += "To lenght cant be 0!\n";
        for(int i = 0; i < entity.getTo().size(); i++)
            if(utilizatorService.findOne(entity.getTo().get(i)) == null)
                msg += "No user with this id: " + entity.getFrom();
        if(!msg.equals(""))
            throw new ValidationException(msg);
    }
}
