package socialnetwork.domain;

public class UserLogin extends Entity<Long>{
    String userName;
    String password;
    Long userId;

    public UserLogin(String userName, String password, Long userId) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                '}';
    }
}
