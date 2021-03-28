package vacante.domain;

//Client(clientId: Long, name: String, fidelityGrade: int [1..100],vârsta:int, hobbies:enum(Reading, music, hiking, walking, extreme sports)
public class Client extends Entity<Long>{
    private Long clientId;
    private String name;
    private Integer fidelityGrade;
    private Integer varsta;
    private Hobby hobby;

    public Client(Long clientId, String name, Integer fidelityGrade, Integer varsta, Hobby hobby) {
        super.setId(clientId);
        this.clientId = clientId;
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.varsta = varsta;
        this.hobby = hobby;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(Integer fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", fidelityGrade=" + fidelityGrade +
                ", varsta=" + varsta +
                ", hobby=" + hobby +
                '}';
    }
}
