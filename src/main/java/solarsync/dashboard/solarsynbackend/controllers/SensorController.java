package solarsync.dashboard.solarsynbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solarsync.dashboard.solarsynbackend.entities.dtos.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private static final Logger logger = LoggerFactory.getLogger(SensorController.class);

    private SensorData latestData;

    @PostMapping("/dht22")
    public ResponseEntity<String> receiveData(@RequestBody SensorData data) {
        this.latestData = data;

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        logger.info("");
        logger.info("======================================================");
        logger.info("          NEW SENSOR DATA RECEIVED FROM DHT22         ");
        logger.info("======================================================");
        logger.info("Time         : {}", now);
        logger.info("Temperature  : {} °C", data.getTemperature());
        logger.info("Humidity     : {} %", data.getHumidity());
        logger.info("Raw Object   : {}", data);
        logger.info("======================================================");
        logger.info("");

        return ResponseEntity.ok("Data received successfully");
    }

    @GetMapping("/dht22/latest")
    public ResponseEntity<SensorData> getLatestData() {
        if (latestData == null) {
            logger.warn("No sensor data available yet.");
            return ResponseEntity.noContent().build();
        }

        logger.info("Returning latest sensor data: {}", latestData);
        return ResponseEntity.ok(latestData);
    }
}