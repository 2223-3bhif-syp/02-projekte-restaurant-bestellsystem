/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Bill {
    private Long id;
    private Long workingNr;
    private Long deskNr;
    //region Constructor
    public Bill(Long id) {
        this.id = id;
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

    public Long getDeskNr() {
        return deskNr;
    }

    public void setDeskNr(Long deskNr) {
        this.deskNr = deskNr;
    }
    //endregion
}
