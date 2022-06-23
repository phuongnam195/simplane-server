package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.FlightAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IFlightAttrRepository extends MongoRepository<FlightAttribute, String> {
    List<FlightAttribute> findAllByTypeAndFlightId(String type, String flightId);

    FlightAttribute findByTypeAndIdTicketClassAndFlightId(String type,String idTicketClass,String flightId);

    void deleteAllByFlightId(String flightId);
}
