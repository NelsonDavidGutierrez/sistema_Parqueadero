package crud.sistema.parqueadero.Service;

import crud.sistema.parqueadero.Model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    public Vehicle guardar( Vehicle vehicle);
    public Optional <Vehicle> get(Integer id);
    public Vehicle update(Vehicle vehicle);
    public void delete(Integer id);
    public List<Vehicle> findAll();
}
