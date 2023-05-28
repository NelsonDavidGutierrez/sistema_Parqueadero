package crud.sistema.parqueadero.Controller;
import crud.sistema.parqueadero.Model.Usuario;
import crud.sistema.parqueadero.Model.Vehicle;
import crud.sistema.parqueadero.Service.UsuarioService;
import crud.sistema.parqueadero.Service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("usuarios", usuarioService.findAllUsuario());
        model.addAttribute("vehiculos", vehicleService.findAll());
        return "administrador/listaVehiculo";
    }
    @GetMapping("/create")
    public  String create(Model model) {
        model.addAttribute("usuarios", new Usuario());
        model.addAttribute("vehiculos", vehicleService.findAll());
        return  "vehiculo/agregarVehiculo";
    }

    @PostMapping("/save")
    public String save(Usuario usuario, Vehicle vehicle, Model model){
        vehicleService.guardar(vehicle);
        usuarioService.guardarUsuario(usuario);
        usuario.setVehicle(Optional.ofNullable(vehicle));

        List<Usuario> usuariosConVehiculos = usuarioService.findAllUsuario();

        model.addAttribute("usuarios", usuariosConVehiculos);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Usuario> optionalUsuario = usuarioService.getUsuario(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            model.addAttribute("usuario", usuario);
            List<Vehicle> vehicles = vehicleService.findAll();
            model.addAttribute("vehiculos", vehicles);
            return "vehiculo/editarVehiculo";
        } else {
            model.addAttribute("errorMessage", "El usuario con el ID " + id + " no se encontró.");
            return "redirect:/vehicles";
        }
    }


    @PostMapping("/update")
    public String update(Vehicle vehicle, Usuario usuario){
        vehicleService.update(vehicle);
        usuarioService.updateUsuario(usuario);
        usuario.setVehicle(Optional.ofNullable(vehicle));
        return "redirect:/vehicles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        Optional<Vehicle> vehicle = vehicleService.get(id);
        if (vehicle != null) {
            Usuario usuario = usuarioService.findByVehicleId(id);
            if (usuario != null) {
                usuario.setVehicle(null);
                usuarioService.guardarUsuario(usuario);
            }
            vehicleService.delete(id);
        }
        return "redirect:/vehicles";
    }
}
