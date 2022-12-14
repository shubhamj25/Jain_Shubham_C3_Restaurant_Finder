import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Spy
    @NotNull
    Restaurant restaurant;

    @BeforeEach
    void initRestaurantService() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Singla Sweets", "Delhi", openingTime, closingTime);
        restaurant.addToMenu("Indian Sizzler", 200);
        restaurant.addToMenu("Mexican lasagne", 300);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant1 = Mockito.spy(restaurant);
        Mockito.when(restaurant1.getCurrentTime()).thenReturn(LocalTime.parse("13:00:00"));
        assertTrue(restaurant1.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant1 = Mockito.spy(restaurant);
        Mockito.when(restaurant1.getCurrentTime()).thenReturn(LocalTime.parse("00:00:00"));
        assertFalse(restaurant1.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Indian Sizzler");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void should_return_500_as_order_value_for_a_set_of_items_with_price_200_and_300() throws itemNotFoundException {
        final ArrayList<String> order = new ArrayList<>();
        order.add("Indian Sizzler");
        order.add("Mexican lasagne");
        int expectedTotal = 500;
        assertEquals(expectedTotal, restaurant.getTotalOrderValue(order));
    }

    @Test
    public void should_throw_exception_while_calculating_order_value_if_any_item_does_not_exist_in_menu() {
        final ArrayList<String> order = new ArrayList<>();
        order.add("Indian Sizzler");
        order.add("Potato Bites");
        assertThrows(itemNotFoundException.class, () ->
                restaurant.getTotalOrderValue(order)
        );
    }
    //<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}