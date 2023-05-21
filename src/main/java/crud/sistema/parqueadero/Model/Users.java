package crud.sistema.parqueadero.Model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table (name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Vehicle vehicle;
}
