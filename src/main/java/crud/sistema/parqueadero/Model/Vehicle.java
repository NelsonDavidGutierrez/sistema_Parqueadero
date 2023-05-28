package crud.sistema.parqueadero.Model;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "vehicleType")
    private String vehicleType;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Usuario usuario;
}
