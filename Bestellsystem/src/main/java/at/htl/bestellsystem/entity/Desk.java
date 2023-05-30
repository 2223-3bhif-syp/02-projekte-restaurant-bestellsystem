/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Desk {

    private Long id;
    private Service service;
    //region Constructors

    public Desk() {
    }

    public Desk(Service service) {
        this.service = service;
    }

//endregion


    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    //endregion


    @Override
    public String toString() {
        return "Desk{" +
                "desk_nr=" + id +
                ", working_nr=" + service.getId() +
                '}';
    }
}
