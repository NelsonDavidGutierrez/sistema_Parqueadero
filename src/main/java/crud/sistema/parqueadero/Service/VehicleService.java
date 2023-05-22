package crud.sistema.parqueadero.Service;

import crud.sistema.parqueadero.Model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    private static final double CAR_HOUR_PRICE = 2500;
    private static final double BIKE_HOUR_PRICE = 1500;
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicle.setEntryTime(LocalDateTime.now());
        vehicles.add(vehicle);
    }

    public void updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setUsername(updatedVehicle.getUsername());
        vehicle.setVehicleType(updatedVehicle.getVehicleType());
    }

    public void deleteVehicle(Long id) {
        vehicles.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    public Vehicle getVehicleById(Long id) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public double calculatePrice(Long id) {
        Vehicle vehicle = getVehicleById(id);
        LocalDateTime exitTime = LocalDateTime.now();
        vehicle.setExitTime(exitTime);
        Duration duration = Duration.between(vehicle.getEntryTime(), exitTime);
        double hours = duration.toHours();
        double pricePerHour = (vehicle.getVehicleType().equalsIgnoreCase("carro")) ? CAR_HOUR_PRICE : BIKE_HOUR_PRICE;
        double totalPrice = hours * pricePerHour;

        return totalPrice;
    }
}
