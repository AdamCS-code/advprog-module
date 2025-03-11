package id.ac.ui.cs.advprog.eshop.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;

public class PaymentRepository {
    public List<Payment> payments;
    public Payment addPayment(String paymentId, Order order, String method, String status, Map<String, String> paymentData) {

        return null;
    } 
    public void setStatus(String status) {

        return;
    }
    public List<Payment> getAllPayment() {
        return payments;
    }
    public Payment getPayment(String paymentId) {
        return null;
    }
}