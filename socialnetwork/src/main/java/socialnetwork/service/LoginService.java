package socialnetwork.service;

import socialnetwork.domain.UserLogin;
import socialnetwork.repository.Repository;

public class LoginService {
    private Repository<Long, UserLogin> repo;

    public LoginService(Repository<Long, UserLogin> repo) {
        this.repo = repo;
    }

    public Iterable<UserLogin> getAll(){
        return repo.findAll();
    }

    public Long login(String userName, String password){
        for(UserLogin u : getAll()){
            if(u.getUserName().equals(userName)){
                if(u.getPassword().equals(password))
                    return u.getUserId();
            }
        }
        return null;
    }
}
