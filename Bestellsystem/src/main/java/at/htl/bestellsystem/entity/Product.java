/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Product {
    private Dish dish;
    private Long pId;

    private String name;
    private double price;

    //region Constructors
    public Product(){}

    public Product(String name, double price,Dish dish) {
        this.dish = dish;
        this.name = name;
        this.price = price;
    }
    //endregion


    //region Getters and setters
    public Long getPId() {
        return pId;
    }

    public void setPId(Long id) {
        this.pId = id;
    }




    public String getName() {
        return name;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
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
        return name;
    }
}
