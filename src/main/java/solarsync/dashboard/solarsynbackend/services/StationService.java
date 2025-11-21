package solarsync.dashboard.solarsynbackend.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solarsync.dashboard.solarsynbackend.entities.dtos.StationResponseDTO;
import solarsync.dashboard.solarsynbackend.entities.Station;
import solarsync.dashboard.solarsynbackend.entities.repositories.StationRepository;
import org.bson.types.ObjectId;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public StationResponseDTO getDashboardStation(String stationId) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(stationId);  // 🔹 convert hex string → ObjectId
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid stationId: " + stationId);
        }

        Station station = stationRepository.findById(String.valueOf(objectId))
                .orElseThrow(() -> new RuntimeException("Station not found: " + stationId));

        return StationResponseDTO.builder()
                .id(station.getId())          // 🔹 back to String for frontend
                .name(station.getName())
                .location(station.getLocation())
                .totalSockets(station.getTotalSockets())
                .maxVoltage(station.getMaxVoltage())
                .occupiedSockets(station.getOccupiedSockets())
                .stateOfCharge(station.getStateOfCharge())
                .voltage(station.getVoltage())
                .lastUpdate(station.getLastUpdate())
                .updatedAt(station.getUpdatedAt())
                .build();
    }
}
