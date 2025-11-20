package solarsync.dashboard.solarsynbackend.entities.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import solarsync.dashboard.solarsynbackend.entities.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
