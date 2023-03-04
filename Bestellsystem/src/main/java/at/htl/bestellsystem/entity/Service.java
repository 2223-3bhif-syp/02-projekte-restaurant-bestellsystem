package at.htl.bestellsystem.entity;

public class Service {
    private Long id;

    private String firstName;
    private String lastName;

    //region constructors
    public Service(Long id) {
        this.id = id;
    }

    public Service(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Service(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Service(String firstName) {
        this.firstName = firstName;
    }
    //endregion

    //region getter and setters
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
    //endregion


    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
