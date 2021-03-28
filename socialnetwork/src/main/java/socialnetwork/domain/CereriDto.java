package socialnetwork.domain;

import java.time.LocalDateTime;

public class CereriDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime data;
    private String status;

    public CereriDto(Long id, String firstName, String lastName, LocalDateTime data, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CereriDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
