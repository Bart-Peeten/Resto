package be.pxl.s2it.repo;

import be.pxl.s2it.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestoRepository extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findByName(String name);

}
