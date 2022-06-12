package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IFlightRepository extends MongoRepository<Flight,String> {
    Flight findByCode(String code);
    Optional<Flight> findById(String id);
}
