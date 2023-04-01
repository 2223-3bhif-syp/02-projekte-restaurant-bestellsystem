/*
    Erstellt von: Balint Balazs
*/
package at.htl.bestellsystem.entity;

public class Invoice {
    private Long id;
    private double price;
    private int amoutOfItem;
    private Bill bill;
    private Product product;

    //region Constructions

    public Invoice() {
    }

    public Invoice(double price, int amoutOfItem, Bill bill, Product product) {
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

    public int getAmoutOfItem() {
        return amoutOfItem;
    }
    public void setAmoutOfItem(int amoutOfItem) {
        this.amoutOfItem = amoutOfItem;
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
                "invoice_nr=" + id +
                ", bill_nr=" + bill.getId() +
                ", item_nr=" + getProduct().getId() +
                ", amout_of_item=" + amoutOfItem +
                ", price=" + price +
                '}';
    }
}
