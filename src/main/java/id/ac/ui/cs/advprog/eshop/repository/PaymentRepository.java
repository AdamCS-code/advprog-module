package id.ac.ui.cs.advprog.eshop.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;

public class PaymentRepository {
    public List<Payment> payments = new ArrayList<>();
    public Payment addPayment(String paymentId, Order order, String method,  Map<String, String> paymentData) {
        Payment payment = new Payment(paymentId, order, method, null, paymentData);

        if (method.equals("BANK_TRANSFER")) {
            boolean hasBankName = paymentData.containsKey("bank_name");
            boolean hasReferenceCode= paymentData.containsKey("referenceCode");
       
            if (hasBankName && hasReferenceCode) {
                order.setOrderStatus("SUCCESS");
                payment.setStatus("SUCCESS");
            } 
            else {
                order.setOrderStatus("REJECTED");
                payment.setStatus("REJECTED");
            }
      
            payments.add(payment);
        }  
        else if (method.equals("VOUCHER")) {
            boolean hasVoucherCode = paymentData.containsKey("voucherCode");
            if (hasVoucherCode) {
                order.setOrderStatus("SUCCESS");
                order.setOrderStatus("SUCCESS");
            } 
            else {
                order.setOrderStatus("REJECTED");
                payment.setStatus("REJECTED");
            }
            payments.add(payment);
        } 
        else {
            throw new IllegalArgumentException();
        }

        return payment;
    } 
    public void setStatus(Payment payment, String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            if (payments.contains(payment)) {
                payment.setStatus(status);
                payment.getOrder().setOrderStatus(status);
            } 
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public List<Payment> getAllPayment() {
        return payments;
    }
    public Payment getPayment(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }
}