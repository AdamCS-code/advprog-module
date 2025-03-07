package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import java.util.HashMap;

public class Payment {
    private String id;
    private Order order;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, Order order, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.status = status;
        this.paymentData = new HashMap<>(paymentData);
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(Map<String, String> paymentData) {
        String newVoucherCode = paymentData.get("voucherCode");

        if (newVoucherCode == null || newVoucherCode.isEmpty() || !newVoucherCode.startsWith("ESHOP")) {
            if (this.paymentData != null && this.paymentData.containsKey("voucherCode")) {
                String existingVoucherCode = this.paymentData.get("voucherCode");
                paymentData.put("voucherCode", existingVoucherCode);
            }
        }

        this.paymentData = new HashMap<>(paymentData);
    }
}