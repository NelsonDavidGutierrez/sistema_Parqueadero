package crud.sistema.parqueadero.Service;

import crud.sistema.parqueadero.Model.Vehicle;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;

@Service
public class VehicleService {
    private static final int HOUR_PRICE = 1500;
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

    public int calculatePrice(Long id) {
        Vehicle vehicle = getVehicleById(id);
        LocalDateTime exitTime = LocalDateTime.now();
        vehicle.setExitTime(exitTime);
        long duration = exitTime.getHour() - vehicle.getEntryTime().getHour();
        return (int) (duration * HOUR_PRICE);
    }
}

