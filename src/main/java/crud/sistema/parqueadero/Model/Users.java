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
    @Column(name = "id", nullable = false)
    private Long Id;
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Vehicle vehicle;
}
