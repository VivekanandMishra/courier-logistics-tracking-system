package jsp.courier.service;

import java.util.List;

import jsp.courier.entity.Warehouse;

public interface WarehouseService {

    Warehouse saveWarehouse(Warehouse warehouse);

    List<Warehouse> getAllWarehouses();

    Warehouse getWarehouseById(Integer id);

    Warehouse updateWarehouse(Integer id, Warehouse warehouse);

    String deleteWarehouse(Integer id);
}