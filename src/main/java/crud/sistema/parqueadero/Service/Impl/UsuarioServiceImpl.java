package crud.sistema.parqueadero.Service.Impl;

import crud.sistema.parqueadero.Model.Usuario;
import crud.sistema.parqueadero.Repository.UsuarioRepositoy;
import crud.sistema.parqueadero.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepositoy usuarioRepository) {
        this.usuarioRepositoy = usuarioRepository;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return (usuarioRepositoy.save(usuario));
    }

    @Override
    public Optional<Usuario> getUsuario(Integer id) {
        return Optional.empty();
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return (usuarioRepositoy.save(usuario));
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepositoy.deleteById(id);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return (usuarioRepositoy.findAll());
    }

    @Override
    public Usuario findByVehicleId(Integer vehicleId) {
        return usuarioRepositoy.findByVehicleId(vehicleId);
    }
}
