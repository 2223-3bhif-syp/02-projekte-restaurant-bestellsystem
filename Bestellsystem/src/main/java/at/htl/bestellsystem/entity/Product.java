/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Product {
    private Long pId;
    private Long dishNr;
    private String name;
    private double price;

    //region Constructors
    public Product(Long pId) {
        this.pId = pId;
    }

    public Product( Long pId,Long dishNr ,  String name, double price) {
        this.pId = pId;
        this.dishNr = dishNr;
        this.name = name;
        this.price = price;
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
        return pId;
    }

    public void setId(Long id) {
        this.pId = id;
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
                "pId=" + pId +
                ", dishNr=" + dishNr +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
