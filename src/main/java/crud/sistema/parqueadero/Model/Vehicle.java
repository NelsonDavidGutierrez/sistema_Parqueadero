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
    @Column(name = "id")
    private Integer Id;

    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    @Column(name = "placa")
    private String placa;

    @Column(name = "vehicleType")
    private String vehicleType;
}
