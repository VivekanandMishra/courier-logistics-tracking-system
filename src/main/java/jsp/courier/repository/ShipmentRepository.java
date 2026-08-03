package jsp.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.courier.entity.Shipment;
import jsp.courier.entity.ShipmentStatus;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findByCustomerId(Long customerId);

    List<Shipment> findByStatus(ShipmentStatus status);

}