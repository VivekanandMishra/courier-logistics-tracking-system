package jsp.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsp.courier.entity.Payment;
import jsp.courier.entity.Shipment;
import jsp.courier.repository.PaymentRepository;
import jsp.courier.repository.ShipmentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    // =========================
    // SAVE PAYMENT
    // =========================
    @Override
    public Payment savePayment(Payment payment) {

        if (payment.getShipment() == null ||
            payment.getShipment().getId() == null) {

            throw new RuntimeException("Shipment ID is required");
        }

        Long shipmentId = payment.getShipment().getId();

        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Shipment not found with id: " + shipmentId
                    )
                );

        payment.setShipment(shipment);

        return paymentRepository.save(payment);
    }

    // =========================
    // GET ALL PAYMENTS
    // =========================
    @Override
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }

    // =========================
    // GET PAYMENT BY ID
    // =========================
    @Override
    public Payment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Payment not found with id: " + id
                    )
                );
    }

    // =========================
    // UPDATE PAYMENT
    // =========================
    @Override
    public Payment updatePayment(Long id, Payment payment) {

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Payment not found with id: " + id
                    )
                );

        // Update normal fields
        existingPayment.setAmount(payment.getAmount());

        existingPayment.setPaymentMethod(
                payment.getPaymentMethod()
        );

        existingPayment.setTransactionId(
                payment.getTransactionId()
        );

        existingPayment.setPaymentStatus(
                payment.getPaymentStatus()
        );

        existingPayment.setPaymentDate(
                payment.getPaymentDate()
        );

        // Update shipment only if provided
        if (payment.getShipment() != null &&
            payment.getShipment().getId() != null) {

            Long shipmentId = payment.getShipment().getId();

            Shipment shipment = shipmentRepository.findById(shipmentId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Shipment not found with id: "
                            + shipmentId
                        )
                    );

            existingPayment.setShipment(shipment);
        }

        return paymentRepository.save(existingPayment);
    }

    // =========================
    // DELETE PAYMENT
    // =========================
    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Payment not found with id: " + id
                    )
                );

        paymentRepository.delete(payment);
    }
}