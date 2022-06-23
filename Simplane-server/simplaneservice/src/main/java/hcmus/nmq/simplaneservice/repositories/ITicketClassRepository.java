package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Airport;
import hcmus.nmq.entities.TicketClass;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 11:46 PM 6/23/2022
 * LeHongQuan
 */

public interface ITicketClassRepository extends MongoRepository<TicketClass, String> {
}