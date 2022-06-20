package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPassengerRepository extends MongoRepository<Passenger,String> {
}
