package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    public PaymentRepository paymentRepository = new PaymentRepository();

    @Override
    public Payment create(Payment payment) {
        paymentRepository.payments.add(payment);
        return payment;
    }

    @Override
    public Payment findById(String id) {
        return paymentRepository.getPayment(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.getAllPayment();
    }

    @Override
    public void update(Payment payment, String newStatus) {
        paymentRepository.setStatus(payment, newStatus);
    }
}