package socialnetwork.validator;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;

public class PrietenieValidator implements Validator<Prietenie> {
    public void validate(Prietenie entity) throws ValidationException {
        String msg = "";
        if(entity.getID() == null)
            msg += "Id nu poate fi null!\n";
        if(entity.getID() < 0)
            msg += "Id nu poate fi negativ!\n";
        if(entity.getU1() == null)
            msg += "Utilizatorul 1 nu poate fi null!\n";
        if(entity.getU2() == null)
            msg += "Utilizatorul 2 nu poate fi null!\n";
        if(entity.getU1() == entity.getU2())
            msg += "Utilizatorul 1 nu poate fi la fel cu utilizatorul 2!\n";
        if(!msg.equals(""))
            throw new ValidationException(msg);
    }
}
