package id.ac.ui.cs.advprog.eshop.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

public class PaymentRepository {

    public List<Payment> payments = new ArrayList<>();

    public Payment addPayment(String paymentId, Order order, String method,  Map<String, String> paymentData) {
        Payment payment = new Payment(paymentId, order, method, null, paymentData);

        if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            
            if (validateBankTransferPayment(paymentData)) {
                setSuccess(payment);
            } 
            else {
                setFailed(payment);
            }
      
            payments.add(payment);
        }  
        else if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            if (validateVoucherPayment(paymentData)) {
                setSuccess(payment);
            } 
            else {
                setFailed(payment);
            }
            payments.add(payment);
        } 
        else {
            throw new IllegalArgumentException();
        }

        return payment;
    } 

    private void setSuccess(Payment payment) {
        payment.getOrder().setOrderStatus(OrderStatus.SUCCESS.getValue());
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
    }

    private void setFailed(Payment payment) {
        payment.getOrder().setOrderStatus(OrderStatus.FAILED.getValue());
        payment.setStatus(PaymentStatus.REJECTED.getValue());
    }

    private boolean validateBankTransferPayment(Map<String, String> paymentData) {
        boolean hasBankName = paymentData.containsKey("bank_name");
        boolean hasReferenceCode= paymentData.containsKey("referenceCode");
        return hasBankName && hasReferenceCode;
    }

    private boolean validateVoucherPayment(Map<String, String> paymentData) {
        return paymentData.containsKey("voucherCode");
    }

    public void setStatus(Payment payment, String status) {
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            setSuccess(payment);
        }
        else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            setFailed(payment);
        } else {
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