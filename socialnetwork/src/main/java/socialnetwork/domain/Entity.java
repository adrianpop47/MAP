package socialnetwork.domain;

import java.io.Serializable;
import java.rmi.server.UID;

public class Entity<ID> implements Serializable {

    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;
    public ID getID(){return id;}
    public void setId(ID id){this.id = id;}
}
