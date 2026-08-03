package jsp.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.courier.entity.Shipment;
import jsp.courier.entity.ShipmentStatus;
import jsp.courier.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/save")
    public ResponseEntity<Shipment> saveShipment(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(shipmentService.saveShipment(shipment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Shipment>> getShipmentByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(shipmentService.getShipmentByCustomerId(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Shipment>> getShipmentByStatus(@PathVariable ShipmentStatus status) {
        return ResponseEntity.ok(shipmentService.getShipmentByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id,
                                                   @RequestBody Shipment shipment) {
        return ResponseEntity.ok(shipmentService.updateShipment(id, shipment));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Shipment> updateShipmentStatus(@PathVariable Long id,
                                                         @RequestParam ShipmentStatus status) {
        return ResponseEntity.ok(shipmentService.updateShipmentStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.ok("Shipment deleted successfully.");
    }
}