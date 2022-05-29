package hcmus.nmq.simplaneservice.repositories.impls;

import hcmus.nmq.simplaneservice.repositories.ISequenceNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 5:31 PM 5/29/2022
 * LeHongQuan
 */

public class BaseRepositoryCustom {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected ISequenceNumberRepository sequenceNumberRepository;
}