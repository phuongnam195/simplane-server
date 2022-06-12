package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface IAirportRepository extends MongoRepository<Airport, String> {
    Optional<Airport> findById(String id);

    Airport findByCode(String code);

    List<Airport> findAllByCode(List<String> codes);
}
