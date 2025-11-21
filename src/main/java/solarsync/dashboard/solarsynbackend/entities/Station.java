package solarsync.dashboard.solarsynbackend.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.*;
import java.time.Instant;

@Document(collection = "stations")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Station {

    @Id
    @Field("_id")
    private ObjectId id;

    //below is my static info
    @NotBlank
    @Field("name")
    private String name;

    @NotBlank
    @Field("location")
    private String location;


    @Min(1)
    @Field("totalSockets")
    private int totalSockets;

    @DecimalMin("0.0")
    @Field("maxVoltage")
    private double maxVoltage; //by the constructor


    //below is the real time info
    @Min(0)
    @Field("occupiedSockets")
    private int occupiedSockets;

    @DecimalMin("0.0")
    @Field("stateOfCharge")
    private double stateOfCharge;

    @DecimalMin("0.0")
    @Field("voltage")
    private double voltage;


//meta data from sensors (les capteurs)
    @Field("lastUpdate")
    private Instant lastUpdate; //for the sensor


    @LastModifiedDate
    @Field("updatedAt")
    private Instant updatedAt; // mongo db updated at document timestamp
}
