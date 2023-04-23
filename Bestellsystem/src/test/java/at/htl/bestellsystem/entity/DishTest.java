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
}