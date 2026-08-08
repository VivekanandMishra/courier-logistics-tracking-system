package jsp.courier.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsp.courier.entity.Shipment;
import jsp.courier.entity.ShipmentStatus;
import jsp.courier.repository.ShipmentRepository;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public Shipment saveShipment(Shipment shipment) {

        shipment.setShippedDate(LocalDateTime.now());

        if (shipment.getStatus() == null) {
            shipment.setStatus(ShipmentStatus.CREATED);
        }

        return shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found with id: " + id));
    }

    @Override
    public List<Shipment> getShipmentByCustomerId(Long customerId) {
        return shipmentRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Shipment> getShipmentByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status);
    }

    @Override
    public Shipment updateShipment(Long id, Shipment shipment) {

        Shipment existingShipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found with id: " + id));

        existingShipment.setShipmentNumber(shipment.getShipmentNumber());
        existingShipment.setCustomer(shipment.getCustomer());
        existingShipment.setDeliveryAgent(shipment.getDeliveryAgent());
        existingShipment.setStatus(shipment.getStatus());
        existingShipment.setSourceLocation(shipment.getSourceLocation());
        existingShipment.setDestinationLocation(shipment.getDestinationLocation());
        existingShipment.setCurrentLocation(shipment.getCurrentLocation());
        existingShipment.setEstimatedDeliveryDate(shipment.getEstimatedDeliveryDate());
        existingShipment.setDeliveredDate(shipment.getDeliveredDate());
        existingShipment.setRemarks(shipment.getRemarks());

        return shipmentRepository.save(existingShipment);
    }

    @Override
    public Shipment updateShipmentStatus(Long id, ShipmentStatus status) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found with id: " + id));

        shipment.setStatus(status);

        if (status == ShipmentStatus.DELIVERED) {
            shipment.setShippedDate(LocalDateTime.now());
        }

        return shipmentRepository.save(shipment);
    }

    @Override
    public void deleteShipment(Long id) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found with id: " + id));

        shipmentRepository.delete(shipment);
    }
}