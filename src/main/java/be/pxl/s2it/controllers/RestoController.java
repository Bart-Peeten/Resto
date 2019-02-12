package be.pxl.s2it.controllers;

import be.pxl.s2it.domain.Restaurant;
import be.pxl.s2it.exceptions.FaultException;
import be.pxl.s2it.interfaces.RestoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "resto/")
public class RestoController {

    @Autowired
    @Qualifier(value = "Resto")
    private RestoManager restoManager;

    @GetMapping(path="all")
    public ResponseEntity getResto(){
        Iterable<Restaurant> restaurants = restoManager.getAllRestaurants();

        if(restaurants != null) {
            return new ResponseEntity(restaurants, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Restaurant> getResto(@PathVariable Long id){
        Restaurant restaurant = restoManager.getRestaurant(id);

        if (restaurant != null) {
            return new ResponseEntity(restaurant, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/post", consumes = "application/json")
    public void postNewRestaurant(@RequestBody @Valid Restaurant restaurant){

        restoManager.addNewRestaurant(restaurant);
    }

    @PutMapping(path = "/put/{id}")
    public ResponseEntity putRestaurant(@PathVariable long id,
                                        @RequestBody Restaurant restaurant){
        try {
            restoManager.putRestaurant(id, restaurant);
        } catch (DataAccessException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (FaultException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path="delete/{id}")
    public ResponseEntity deleteRestoById(@PathVariable Long id){
        restoManager.deleteRestaurantById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
