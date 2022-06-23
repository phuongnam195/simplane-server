package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.FlightAdjustment;
import hcmus.nmq.entities.FlightAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IFlightAdjustmentRepository extends MongoRepository<FlightAdjustment, String> {
    List<FlightAdjustment> findAllByFlightId(String flightId);

    void deleteAllByFlightId(String flightId);
}
