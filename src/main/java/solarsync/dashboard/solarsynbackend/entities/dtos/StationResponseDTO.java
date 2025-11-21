package solarsync.dashboard.solarsynbackend.entities.dtos;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.Instant;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class StationResponseDTO {

    private ObjectId id;
    private String name;
    private String location;

    // static info
    private int totalSockets;
    private double maxVoltage;

    // real-time info
    private int occupiedSockets;
    private double stateOfCharge;
    private double voltage;

    // metadata
    private Instant lastUpdate;
    private Instant updatedAt;
}

