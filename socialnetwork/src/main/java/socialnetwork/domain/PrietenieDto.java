package socialnetwork.domain;

import java.time.LocalDateTime;

public class PrietenieDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime data;

    public PrietenieDto(Long id, String firstName, String lastName, LocalDateTime data) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PrietenieDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", data=" + data +
                '}';
    }
}
