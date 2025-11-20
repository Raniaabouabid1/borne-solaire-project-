package solarsync.dashboard.solarsynbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solarsync.dashboard.solarsynbackend.entities.dtos.AuthRequest;
import solarsync.dashboard.solarsynbackend.entities.dtos.AuthResponse;
import solarsync.dashboard.solarsynbackend.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // if frontend separate
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
