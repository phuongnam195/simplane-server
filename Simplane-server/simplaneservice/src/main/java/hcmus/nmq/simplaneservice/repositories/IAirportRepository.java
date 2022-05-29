package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IAirportRepository extends MongoRepository<Airport,String> {
    Airport findByCode(String code);
}
