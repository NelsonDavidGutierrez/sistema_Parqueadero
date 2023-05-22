package crud.sistema.parqueadero.Controller;

import crud.sistema.parqueadero.Model.Vehicle;
import crud.sistema.parqueadero.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

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

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehiculo/editarVehiculo";
    }

    @PostMapping("/edit/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle updatedVehicle) {
        vehicleService.updateVehicle(id, updatedVehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @GetMapping("/calculate-price/{id}")
    public String calculatePrice(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(vehicle.getEntryTime(), now);
        double hours = duration.toHours();
        double pricePerHour = (vehicle.getVehicleType().equals("carro")) ? 2500 : 1500;
        double totalPrice = hours * pricePerHour;

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("entryTime", vehicle.getEntryTime());
        model.addAttribute("exitTime", now);
        model.addAttribute("hours", hours);
        model.addAttribute("pricePerHour", pricePerHour);
        model.addAttribute("totalPrice", totalPrice);

        return "vehiculo/precioAPagar";
    }
}
