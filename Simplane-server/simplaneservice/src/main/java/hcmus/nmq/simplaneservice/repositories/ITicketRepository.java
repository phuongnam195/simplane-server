package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ITicketRepository  extends MongoRepository<Ticket,String>,ITicketRepositoryCustom {
    Optional<Ticket> findById(String id);
}
