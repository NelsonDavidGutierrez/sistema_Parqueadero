package crud.sistema.parqueadero.Repository;

import crud.sistema.parqueadero.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Integer> {
    Usuario findByVehicleId(Integer vehicleId);
}
