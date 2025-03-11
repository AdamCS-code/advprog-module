package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.*;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {
    @InjectMocks
    public PaymentRepository paymentRepository;
    public Payment payment;
    public Order order;
    @BeforeEach
    void setUp() {
        List<Product> products1 = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("product1");
        product1.setProductQuantity(10);
        products1.add(product1);

        Product product2 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("product2");
        product1.setProductQuantity(9);
        products1.add(product2);

        Product product3 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("product3");
        product1.setProductQuantity(8);
        products1.add(product3);

        order = new Order (UUID.randomUUID().toString(), products1, (long) 1741590554, "Adam");
        Map<String, String> paymentData = new HashMap<String, String>();

        paymentData.put("bank_name", "BCA");
        paymentData.put("referenceCode", "BCARAMAH");
        payment = new Payment("", order, "BANK_TRANSFER", "SUCCESS", paymentData);

        paymentRepository.payments.add(payment);

    }

    @Test
    void testAddPayment() {
        Order newOrder = new Order (
            UUID.randomUUID().toString(), payment.getOrder().getProducts(), (long) 1741625920, "Caldipawell"
        );
        Payment newPayment = paymentRepository.addPayment(payment.getId(), newOrder, PaymentMethod.BANK_TRANSFER.getValue(), payment.getPaymentData());
        
        assertEquals(newPayment.getOrder(), newOrder);
        assertEquals(newPayment.getStatus(), PaymentStatus.SUCCESS.getValue());
        assertEquals(newPayment.getPaymentData(), payment.getPaymentData());
    }

    @Test
    void testSetValidStatusReject() {
        paymentRepository.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(payment.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getOrderStatus());
    }

    @Test
    void testSetValidStatusSuccess() {
        paymentRepository.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getStatus(), PaymentStatus.SUCCESS.getValue());
        assertEquals(payment.getOrder().getOrderStatus(), OrderStatus.SUCCESS.getValue()); 
 
    }

    @Test
    void testSetInvalidStatus() {
        String invalidStatus = "PACIL";
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.setStatus(payment, invalidStatus));
    }

    @Test 
    void testGetPaymentNotExist() {
        String paymentId = "payment-id-baru";

        Payment searchPayment = paymentRepository.getPayment(paymentId);
        assertNull(searchPayment);
    }

    @Test 
    void testGetPaymentExist() {
        String paymentId = payment.getId();

        Payment searchPayment = paymentRepository.getPayment(paymentId);

        assertEquals(searchPayment.getId(), payment.getId());
        assertEquals(searchPayment.getMethod(), payment.getMethod());
        assertEquals(searchPayment.getPaymentData(), payment.getPaymentData());
    }

    @Test
    void testGetAllPayment() {
        
    int numberOfPayment;
        List<Product> products2 = new ArrayList<>();

        Product product4 = new Product();
        product4.setProductId(UUID.randomUUID().toString());
        product4.setProductName("product4");
        product4.setProductQuantity(7);
        products2.add(product4);

        Order order2 = new Order(UUID.randomUUID().toString(), products2, (long) 1741625920, "Sembiring");
        Map<String, String> paymentData2 = new HashMap<String, String>();

        paymentData2.put("bank_name", "BNI");
        paymentData2.put("referenceCode", "BNICERIA");
        
        Payment payment2 = new Payment(UUID.randomUUID().toString(), order2, "BANK_TRANSFER", "SUCCESS", paymentData2);

        paymentRepository.payments.add(payment2);

        List<Payment> allpayment = paymentRepository.getAllPayment();

        numberOfPayment = allpayment.size();
        assertEquals(numberOfPayment, 2);
    }
}