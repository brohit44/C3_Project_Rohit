import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;
    RestaurantService service = new RestaurantService();
    LocalTime openingTime;
    LocalTime closingTime;

    @BeforeEach
    public void setUp(){
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant mockedRestaurant = Mockito.mock(Restaurant.class);
        when(mockedRestaurant.isRestaurantOpen()).thenReturn(true);
        boolean isRestaurantOpen = mockedRestaurant.isRestaurantOpen();

        assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant mockedRestaurant = Mockito.mock(Restaurant.class);
        when(mockedRestaurant.isRestaurantOpen()).thenReturn(false);
        boolean isRestaurantOpen = mockedRestaurant.isRestaurantOpen();

        assertFalse(isRestaurantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
       int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<<<<<<<<<<<<<<<<<Menu Cart>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void get_total_cost_of_items_chosen_from_menu_should_return_sum_of_items_selected(){
        ArrayList<String> sampleItem = new ArrayList<>();
        sampleItem.add("Sweet corn soup");
        sampleItem.add("Vegetable lasagne");
        int orderTotal = restaurant.getTotalCostOfItemsChosenFromMenu(sampleItem);
        assertEquals(388,orderTotal);
    }
    // <<<<<<<<<<<<<<<<<<<<<<<Menu Cart>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}