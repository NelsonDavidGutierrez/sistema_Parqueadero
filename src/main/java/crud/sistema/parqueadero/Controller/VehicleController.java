package crud.sistema.parqueadero.Controller;
import crud.sistema.parqueadero.Model.Vehicle;
import crud.sistema.parqueadero.Service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("vehiculos", vehicleService.findAll());
        return "vehiculo/vehiculoDetalle";
    }
    @GetMapping("/create")
    public  String create() { return  "vehiculo/agregarVehiculo";}

    @PostMapping("/save")
    public String save(Vehicle vehicle) {
        Vehicle o = new Vehicle(1, "", "", "");
        vehicle.setNombreUsuario(String.valueOf(o));
        vehicleService.guardar(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Vehicle> optionalVehicle = vehicleService.get(id);
        optionalVehicle.ifPresentOrElse(
                vehicle -> {
                    LOGGER.info("Vehiculo buscado: {}", vehicle);
                    model.addAttribute("vehiculo", vehicle);
                },
                () -> {
                    model.addAttribute("errorMessage", "El vehículo con el ID " + id + " no se encontró.");
                }
        );
        return "vehiculo/editarVehiculo";
    }


    @PostMapping("/update")
    public String update(Vehicle vehicle){
        vehicleService.update(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        vehicleService.delete(id);
        return "redirect:/vehicles";
    }
}
