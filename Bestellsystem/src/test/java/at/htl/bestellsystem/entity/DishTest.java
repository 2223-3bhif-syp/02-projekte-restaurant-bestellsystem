package at.htl.bestellsystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    @Test
    void createDish() {
        Dish dish = new Dish("Vorspeise");

        assertThat(dish.getId()).isEqualTo(null);
        assertThat(dish.getName()).isEqualTo("Vorspeise");
    }

    @Test
    void simpleConstructorGetters(){
        //arrange
        Dish dish = new Dish("Vorspeise");

        //act

        //assert
        assertThat(dish.getId()).isNull();
        assertThat(dish.getName()).isEqualTo("Vorspeise");

    }

    @Test
    void simpleConstructorSetters(){
        //arrange
        Dish dish = new Dish("Nachspeise");

        //act
        dish.setId(307L);
        dish.setName("Hauptspeise");
        //assert
        assertThat(dish.getId()).isEqualTo(307L);
        assertThat(dish.getName()).isEqualTo("Hauptspeise");
    }

    @Test
    void defaultConstructorGetters(){
        //arrange
        Dish dish = new Dish();

        //act

        //assert
        assertThat(dish.getId()).isNull();
        assertThat(dish.getName()).isNull();
    }

    @Test
    void defaultConstructorSetters(){
        //arrange
        Dish dish = new Dish();

        //act
        dish.setId(34L);
        dish.setName("Dessert");

        //assert
        assertThat(dish.getId()).isEqualTo(34L);
        assertThat(dish.getName()).isEqualTo("Dessert");

    }

}

