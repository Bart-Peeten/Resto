package be.pxl.s2it.service;

import be.pxl.s2it.domain.Restaurant;
import be.pxl.s2it.exceptions.FaultException;
import be.pxl.s2it.interfaces.RestoManager;
import be.pxl.s2it.repo.RestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Resto")
public class RestoManagerImpl implements RestoManager {

    @Autowired
    private RestoRepository repo;

    @Secured("ROLE_USER")
    public Restaurant getRestaurant(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Secured("ROLE_USER")
    public Iterable<Restaurant> getAllRestaurants(){
        return repo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Restaurant addNewRestaurant(Restaurant restaurant){

        Restaurant restaurant1;
        restaurant1 = repo.save(restaurant);

        return restaurant1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRestaurantById(Long id){
        repo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class,
                    propagation = Propagation.REQUIRES_NEW,
                    isolation = Isolation.REPEATABLE_READ)
    @Secured("ROLE_ADMIN")
    public void putRestaurant(long id, Restaurant restaurant) throws DataAccessException, FaultException {

        // Throw new FaultException()
        if (id == -1){
            throw new FaultException("Het restaurant id is niet correct!");
        } else {
            repo.save(restaurant);
        }
    }
}
