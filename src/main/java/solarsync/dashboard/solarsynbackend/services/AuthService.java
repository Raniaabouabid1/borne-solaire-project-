package solarsync.dashboard.solarsynbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solarsync.dashboard.solarsynbackend.entities.dtos.AuthResponse;
import solarsync.dashboard.solarsynbackend.entities.dtos.AuthRequest;
import solarsync.dashboard.solarsynbackend.entities.User;
import solarsync.dashboard.solarsynbackend.entities.repositories.UserRepository;
import solarsync.dashboard.solarsynbackend.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        boolean passwordMatches = passwordEncoder.matches(
                request.password(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
