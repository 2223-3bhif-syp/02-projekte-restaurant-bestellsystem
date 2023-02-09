/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Desk {

    private Long id;
    private Long workingNr;

    //region Constructors
    public Desk(Long id) {
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
    //endregion


    @Override
    public String toString() {
        return "Desk{" +
                "id=" + id +
                ", workingNr=" + workingNr +
                '}';
    }
}
