package socialnetwork.repository.file;

import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.UserLogin;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoginFile extends AbstractFileRepository<Long, UserLogin>{
    public LoginFile(String fileName) {
        super(fileName);
    }

    @Override
    public UserLogin extractEntity(List<String> attributes) throws ParseException {
        UserLogin userLogin = new UserLogin(attributes.get(0),attributes.get(1), Long.parseLong(attributes.get(2)));
        userLogin.setId(Long.parseLong(attributes.get(2)));
        return userLogin;
    }

    @Override
    protected String createEntityAsString(UserLogin entity) {
        return entity.getUserName()+";"+entity.getPassword()+";"+entity.getUserId();
    }
}
