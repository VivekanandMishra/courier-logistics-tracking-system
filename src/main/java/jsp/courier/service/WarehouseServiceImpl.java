package jsp.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsp.courier.entity.Warehouse;
import jsp.courier.repository.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse getWarehouseById(Integer id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse updateWarehouse(Integer id, Warehouse warehouse) {

        Warehouse existingWarehouse = warehouseRepository.findById(id).orElse(null);

        if (existingWarehouse != null) {
            existingWarehouse.setName(warehouse.getName());
            existingWarehouse.setWarehouseCode(warehouse.getWarehouseCode());
            existingWarehouse.setLocation(warehouse.getLocation());
            existingWarehouse.setCapacity(warehouse.getCapacity());

            return warehouseRepository.save(existingWarehouse);
        }

        return null;
    }

    @Override
    public String deleteWarehouse(Integer id) {

        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);

        if (warehouse != null) {
            warehouseRepository.delete(warehouse);
            return "Warehouse deleted successfully";
        }

        return "Warehouse not found";
    }
}