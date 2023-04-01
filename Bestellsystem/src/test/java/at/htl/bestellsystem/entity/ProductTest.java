package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void createProduct() {
        Dish dish = new Dish("Vorspeise");
        Product product = new Product("Zwiebelsuppe",5.60,dish);

        assertThat(product.getId()).isEqualTo(null);
        assertThat(product.getPrice()).isEqualTo(5.60);
        assertThat(product.getName()).isEqualTo("Zwiebelsuppe");
        assertThat(product.getDish()).isEqualTo(dish);
    }
}