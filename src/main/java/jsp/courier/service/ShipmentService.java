package jsp.courier.service;

import java.util.List;

import jsp.courier.entity.Shipment;
import jsp.courier.entity.ShipmentStatus;

public interface ShipmentService {

    // Save Shipment
    Shipment saveShipment(Shipment shipment);

    // Get All Shipments
    List<Shipment> getAllShipments();

    // Get Shipment By Id
    Shipment getShipmentById(Long id);

    // Get Shipments By Customer Id
    List<Shipment> getShipmentByCustomerId(Long customerId);

    // Get Shipments By Status
    List<Shipment> getShipmentByStatus(ShipmentStatus status);

    // Update Shipment
    Shipment updateShipment(Long id, Shipment shipment);

    // Update Shipment Status
    Shipment updateShipmentStatus(Long id, ShipmentStatus status);

    // Delete Shipment
    void deleteShipment(Long id);
}