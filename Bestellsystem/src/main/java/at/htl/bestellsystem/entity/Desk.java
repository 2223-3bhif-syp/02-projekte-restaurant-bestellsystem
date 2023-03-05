/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Desk {

    private Long id;
    private Long workingNr;

    private Service service;
    //region Constructors

    public Desk() {
    }

    public Desk(Long id) {
        this.id = id;
    }

    public Desk(Long id, Service service) {
        this.id = id;
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

    public Long getWorkingNr() {
        return workingNr;
    }

    public void setWorkingNr(Long workingNr) {
        this.workingNr = workingNr;
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
                "id=" + id +
                ", workingNr=" + workingNr +
                '}';
    }
}
