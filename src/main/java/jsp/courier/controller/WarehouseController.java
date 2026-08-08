package jsp.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jsp.courier.entity.Warehouse;
import jsp.courier.service.WarehouseService;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
//save Data 
    @PostMapping("/save")
    public Warehouse saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }
//get data
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }
//get by id
    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id);
    }
//update by id
    @PutMapping("/{id}")
    public Warehouse updateWarehouse(@PathVariable Integer id,
                                     @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id, warehouse);
    }
// delete by id
    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable Integer id) {
        return warehouseService.deleteWarehouse(id);
    }
}