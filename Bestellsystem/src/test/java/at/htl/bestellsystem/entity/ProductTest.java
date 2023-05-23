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

    @Test
    void simpleConstructorGetters(){
        //arrange
        Dish dish = new Dish("Vorspeise");
        Product product = new Product("Zwiebelsuppe",5.60,dish);

        //act

        //assert
        assertThat(product.getId()).isEqualTo(null);
        assertThat(product.getPrice()).isEqualTo(5.60);
        assertThat(product.getName()).isEqualTo("Zwiebelsuppe");
        assertThat(product.getDish()).isEqualTo(dish);
    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Dish dish = new Dish("Vorspeise");
        Dish dish2 = new Dish("Nachspeise");
        Product product = new Product("Zwiebelsuppe",5.60,dish);

        //act
        product.setId(2L);
        product.setName("Kuchen");
        product.setPrice(5.5);
        product.setDish(dish2);

        //assert
        assertThat(product.getId()).isEqualTo(2L);
        assertThat(product.getPrice()).isEqualTo(5.5);
        assertThat(product.getName()).isEqualTo("Kuchen");
        assertThat(product.getDish()).isEqualTo(dish2);
    }

    @Test
    void defaultConstructorGetters(){
        //arrange
        Product product = new Product();

        //act

        //assert
        assertThat(product.getId()).isNull();
        assertThat(product.getName()).isNull();
        assertThat(product.getPrice()).isEqualTo(0.0);
        assertThat(product.getDish()).isNull();
    }

    @Test
    void defaultConstructorSetters(){
        //arrange
        Product product = new Product();
        Dish dish = new Dish("Vorspeise");

        //act
        product.setId(2L);
        product.setName("Kuchen");
        product.setPrice(5.5);
        product.setDish(dish);

        //assert
        assertThat(product.getId()).isEqualTo(2L);
        assertThat(product.getPrice()).isEqualTo(5.5);
        assertThat(product.getName()).isEqualTo("Kuchen");
        assertThat(product.getDish()).isEqualTo(dish);
    }
}