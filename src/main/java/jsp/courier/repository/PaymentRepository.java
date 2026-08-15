package jsp.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courier.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByShipmentId(Long shipmentId);

//    List<Payment> findByCustomerId(Long customerId);
    
}