package solarsync.dashboard.solarsynbackend.entities;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Document(collection = "users")
@Builder
public class User {

    @Id
    @Field("_id")
    private String id;

    @Field("firstName")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Field("lastName")
    @NotBlank(message ="Last name is required")
    private String lastName;

    @Field("email")
    @NotBlank(message = "Email is required")
    @Email
    @Indexed(unique = true)
    private String email;

    @Field("password")
    @NotBlank @Size(min = 8)
    @Pattern(
            regexp     = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$",
            message    = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one special character"
    )
    private String password;

    @Field("stationId")
    private String stationId;
}




