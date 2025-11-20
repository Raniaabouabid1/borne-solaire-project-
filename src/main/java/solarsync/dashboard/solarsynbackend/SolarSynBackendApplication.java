package solarsync.dashboard.solarsynbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import solarsync.dashboard.solarsynbackend.entities.Station;
import solarsync.dashboard.solarsynbackend.entities.User;
import solarsync.dashboard.solarsynbackend.entities.repositories.StationRepository;
import solarsync.dashboard.solarsynbackend.entities.repositories.UserRepository;
import java.util.List;


@SpringBootApplication
public class SolarSynBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolarSynBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository,
                               StationRepository stationRepository) {
        return args -> {

            Station station = stationRepository.findAll().stream().findFirst().orElse(null);

            if (station == null) {
                station = Station.builder()
                        .name("Mantes-la-Jolie Station")
                        .location("Mantes-la-Jolie, Île-de-France, Paris")
                        .totalSockets(3)
                        .maxVoltage(12.0)
                        .occupiedSockets(0)
                        .stateOfCharge(100.0)
                        .voltage(12.0)
                        .lastUpdate(null)
                        .build();

                station = stationRepository.save(station); // now we have an id
            }

            if (userRepository.count() == 0) {

                User u1 = User.builder()
                        .firstName("Rania")
                        .lastName("Bouabid")
                        .email("rania@solarsync.app")
                        .password("Password@123")  
                        .stationId(station.getId())
                        .build();

                User u2 = User.builder()
                        .firstName("Alice")
                        .lastName("Durand")
                        .email("alice@solarsync.app")
                        .password("Password@123")
                        .stationId(station.getId())
                        .build();

                User u3 = User.builder()
                        .firstName("Bob")
                        .lastName("Martin")
                        .email("bob@solarsync.app")
                        .password("Password@123")
                        .stationId(station.getId())
                        .build();

                userRepository.saveAll(List.of(u1, u2, u3));
            }
        };
    }
}
