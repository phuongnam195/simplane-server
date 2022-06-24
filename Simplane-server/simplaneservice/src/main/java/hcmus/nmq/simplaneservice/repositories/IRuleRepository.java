package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Passenger;
import hcmus.nmq.entities.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRuleRepository extends MongoRepository<Rule,String> {
}
