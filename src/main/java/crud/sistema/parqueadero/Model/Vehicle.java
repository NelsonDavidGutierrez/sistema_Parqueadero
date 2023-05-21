package crud.sistema.parqueadero.Model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "vehicles")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String vehicleType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
