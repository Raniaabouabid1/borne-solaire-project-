package solarsync.dashboard.solarsynbackend.entities.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import solarsync.dashboard.solarsynbackend.entities.Station;

public interface StationRepository extends MongoRepository<Station, String> {
    Station findFirstByOrderByIdAsc();
}
