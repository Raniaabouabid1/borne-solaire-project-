package solarsync.dashboard.solarsynbackend.entities.dtos;

public record AuthRequest(
        String email,
        String password
) {}

