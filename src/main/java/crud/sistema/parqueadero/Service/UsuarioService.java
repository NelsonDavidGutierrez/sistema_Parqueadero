package crud.sistema.parqueadero.Service;

import crud.sistema.parqueadero.Model.Usuario;
import crud.sistema.parqueadero.Repository.UsuarioRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario);
    public Optional<Usuario> getUsuario(Integer id);
    public Usuario updateUsuario(Usuario usuario);
    public void deleteUsuario(Integer id);
    public List<Usuario> findAllUsuario();
    public Usuario findByVehicleId(Integer vehicleId);
}
