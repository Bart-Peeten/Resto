package be.pxl.s2it.interfaces;

import be.pxl.s2it.domain.Restaurant;
import be.pxl.s2it.exceptions.FaultException;

public interface RestoManager {

    Restaurant getRestaurant(Long id);
    Iterable<Restaurant> getAllRestaurants();
    Restaurant addNewRestaurant(Restaurant restaurant);
    void deleteRestaurantById(Long id);

    void putRestaurant(long id, Restaurant restaurant) throws FaultException;
}
