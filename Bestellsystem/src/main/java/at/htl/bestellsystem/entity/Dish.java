/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Dish {
    private String name;
    private Long id;

    //region Constructors
    public Dish(Long id) {
        this.id = id;
    }

    public Dish(String name) {
        this.name = name;
    }
    //endregion

    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion


    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
