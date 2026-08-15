package jsp.courier.service;

import java.util.List;

import jsp.courier.entity.Payment;

public interface PaymentService {

    Payment savePayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}