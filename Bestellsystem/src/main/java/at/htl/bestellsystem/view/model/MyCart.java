
package at.htl.bestellsystem.view.model;

        import at.htl.bestellsystem.entity.Product;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;

public class MyCart {
    private static MyCart instance;
    private ObservableList<Product> cartItems;

    private MyCart() {
        cartItems = FXCollections.observableArrayList();
    }

    public static MyCart getInstance() {
        if (instance == null) {
            instance = new MyCart();
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public ObservableList<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
