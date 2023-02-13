/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Product {
    private Long id;
    private Long dishNr;
    private String name;
    private double price;

    //region Constructors

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(double price) {
        this.price = price;
    }
    //endregion


    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDishNr() {
        return dishNr;
    }

    public void setDishNr(Long dishNr) {
        this.dishNr = dishNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //endregion


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", dishNr=" + dishNr +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
