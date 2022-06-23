package hcmus.nmq.simplaneservice.repositories;

import hcmus.nmq.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User,String>,IUserRepositoryCustom {
    User findByFullname(String fullname);
    
    User findByUsername(String username);
}
