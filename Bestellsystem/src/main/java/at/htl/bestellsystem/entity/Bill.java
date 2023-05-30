/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Bill {
    private Long id;
    private Desk desk;
    private  Service service;

    //region Constructor
    public Bill() {
    }

    public Bill(Desk desk, Service service) {
        this.desk = desk;
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

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public Desk getDesk() {
        return desk;
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
        return "Bill{" +
                "bill_nr=" + id +
                ", workingNr='" + getService().getId() + '\'' +
                ", deskNr='" + getDesk().getId() + '\'' +
                '}';
    }
}
