/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Invoice {
    private Long id;

    private double price;
    private Long itemNr;
    private Long amoutOfItem;
    private Bill bill;
    private Product product;

    //region Constructions

    public Invoice() {
    }


    public Invoice(Long id) {
        this.id = id;
    }

    public Invoice(double price) {
        this.price = price;
    }

    public Invoice(Long id, double price, Long amoutOfItem, Bill bill, Product product) {
        this.id = id;
        this.price = price;
        this.amoutOfItem = amoutOfItem;
        this.bill = bill;
        this.product = product;
    }

    //endregion


    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public void setItemNr(Long itemNr) {
        this.itemNr = itemNr;
    }

    public Long getAmoutOfItem() {
        return amoutOfItem;
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
        return "Invoice{" +
                "id=" + id +
                ", price=" + price +
                ", itemNr=" + itemNr +
                ", amoutOfItem=" + amoutOfItem +
                ", bill=" + bill +
                ", product=" + product +
                '}';
    }
}
