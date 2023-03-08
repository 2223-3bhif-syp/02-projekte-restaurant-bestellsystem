/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Bill {
    private Long id;

    private Desk desk;
    private  Service service;
    //region Constructor
    public Bill(Long id) {
        this.id = id;
    }

    public Bill() {
    }

    public Bill(Long id,  Desk desk, Service service) {
        this.id = id;
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
}
