package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.SequenceNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ISequenceNumberRepository extends MongoRepository<SequenceNumber, String>, ISequenceNumberRepositoryCustom {
    SequenceNumber findBySeqName(String sequenceName);
}
