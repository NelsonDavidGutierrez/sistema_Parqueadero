package crud.sistema.parqueadero.Service.Impl;

import crud.sistema.parqueadero.Model.Vehicle;
import crud.sistema.parqueadero.Service.VehicleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl extends VehicleService {
    private static final int HOUR_PRICE = 1500;
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicle.setEntryTime(LocalDateTime.now());
        vehicles.add(vehicle);
    }

    @Override
    public void updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setUsername(updatedVehicle.getUsername());
        vehicle.setVehicleType(updatedVehicle.getVehicleType());
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicles.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No funciona"));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    @Override
    public int calculatePrice(Long id) {
        Vehicle vehicle = getVehicleById(id);
        LocalDateTime exitTime = LocalDateTime.now();
        vehicle.setExitTime(exitTime);
        long duration = exitTime.getHour() - vehicle.getEntryTime().getHour();
        return (int) (duration * HOUR_PRICE);
    }
}

