package crud.sistema.parqueadero.Service;

import crud.sistema.parqueadero.Model.Vehicle;

import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);

    void updateVehicle(Long id, Vehicle updatedVehicle);

    void deleteVehicle(Long id);

    Vehicle getVehicleById(Long id);

    List<Vehicle> getAllVehicles();

    double calculatePrice(Long id);
}
