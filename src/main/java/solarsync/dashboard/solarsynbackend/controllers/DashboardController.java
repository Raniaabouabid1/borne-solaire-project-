package solarsync.dashboard.solarsynbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solarsync.dashboard.solarsynbackend.entities.dtos.StationResponseDTO;
import solarsync.dashboard.solarsynbackend.services.StationService;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // adapt for your frontend
public class DashboardController {

    private final StationService stationService;

    @GetMapping("/{stationId}")
    public ResponseEntity<StationResponseDTO> getDashboardStation(
            @PathVariable String stationId) {

        StationResponseDTO dto = stationService.getDashboardStation(stationId);
        return ResponseEntity.ok(dto);
    }
}


