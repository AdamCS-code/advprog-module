package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);
    Payment findById(String id);
    List<Payment> findAll();
    void update(Payment payment, String newStatus);
}
