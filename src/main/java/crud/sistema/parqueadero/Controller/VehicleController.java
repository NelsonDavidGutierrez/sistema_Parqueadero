package crud.sistema.parqueadero.Controller;

import crud.sistema.parqueadero.Model.Vehicle;
import crud.sistema.parqueadero.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String getAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "administrador/listaVehiculo";
    }

    @GetMapping("/{id}")
    public String getVehicleById(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("price", vehicleService.calculatePrice(id));
        return "administrador/vehiculoDetalle";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehiculo/agregarVehiculo";
    }

    @PostMapping("/add")
    public String addVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehiculo/editarVehiculo";
    }

    @PostMapping("/edit")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle updatedVehicle) {
        vehicleService.updateVehicle(id, updatedVehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/delete")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }
}
