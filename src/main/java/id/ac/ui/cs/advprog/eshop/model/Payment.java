package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;


@Getter
@Setter
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

    public void setMethod(String paymentMethod) {
        if (!PaymentMethod.contains(paymentMethod)) {
            throw new IllegalArgumentException();
        }
        else {
            this.method = paymentMethod;
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (!paymentData.containsKey("voucherCode")) {
            throw new IllegalArgumentException();
        }

        String voucherCode = paymentData.get("voucherCode");

        if (voucherCode == null) {
            throw new IllegalArgumentException();
        }

        if (voucherCode.equals("")) {
            throw new IllegalArgumentException();
        }
        
        if (voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            throw new IllegalArgumentException();
        }

        int countNumericalCharacter = checkNumericalCharacter(voucherCode);
        if (countNumericalCharacter != 8) {
            throw new IllegalArgumentException(); 
        }


        this.paymentData = new HashMap<>(paymentData);
    }

    private int checkNumericalCharacter(String voucherCode) {
        int count = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            char currentChar = voucherCode.charAt(i);
            if (Character.isDigit(currentChar)) {
                count++;
            } 
        }
        return count;
    }
}