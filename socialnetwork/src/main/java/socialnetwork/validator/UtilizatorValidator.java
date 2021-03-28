package socialnetwork.validator;

import socialnetwork.domain.Utilizator;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        String msg = "";
       if(entity.getID() == null)
           msg += "Id nu poate fi null!\n";
       if(entity.getID() < 0)
            msg += "Id nu poate fi negativ!\n";
       if(entity.getFirstName().equals(""))
           msg += "Numele nu poate fi null!\n";
       if(entity.getLastName().equals(""))
           msg += "Prenumele nu poate fi null!\n";
       if(!msg.equals(""))
           throw new ValidationException(msg);
    }
}
