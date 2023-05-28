package crud.sistema.parqueadero.Model;

import lombok.*;
import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "usuarios")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "fullNombre")
    private String fullNombre;

    @OneToOne
    private Vehicle vehicle;

    public void setVehicle(Optional<Vehicle> vehicle) {
    }
}

