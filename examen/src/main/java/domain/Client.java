package domain;

public class Client extends Entity<String>{
    private String username;
    private String name;

    public Client(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
