package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
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

        List<Product> products2 = new ArrayList<>();

        Product product4 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("product4");
        product1.setProductQuantity(7);
        products2.add(product4);

        Order order2 = new Order(UUID.randomUUID().toString(), products2, (long) 1741625920, "Sembiring");
        Map<String, String> paymentData2 = new HashMap<String, String>();

        paymentData.put("bank_name", "BNI");
        paymentData.put("referenceCode", "BNICERIA");
        
        Payment payment2 = new Payment("", order2, "BANK_TRANSFER", "SUCCESS", paymentData2);


    }

    @Test
    void testAddPayment() {
        Order newOrder = new Order (
            UUID.randomUUID().toString(), products, (long) 1741625920, "Caldipawell"
        );
        Payment newPayment = paymentRepository.addPayment(newOrder, "SUCCESS", payment.getPaymentData());
        
        assertEquals(newPayment.getOrder(), newOrder);
        assertEquals(newPayment.getStatus(), "SUCCESS");
        assertEquals(newPayment.getPaymentData(), payment.getPaymentData());
    }

    @Test
    void testSetValidStatusReject() {
        Map<String, String> pdata = new HashMap(payment.getPaymentData());
        String pmethod = new String(payment.getMethod());

        paymentRepository.setStatus("REJECTED");

        assertEquals(payment.getStatus(), "REJECTED");
        assertEquals(payment.getOrder().getOrderStatus(), "FAILED");
        assertEquals(payment.getPaymentData(), pdata);
        assertEquals(payment.getMethod(), pmethod);
    }

    @Test
    void testSetValidStatusSuccess() {
        Map<String, String> pdata = new HashMap(payment.getPaymentData());
        String pmethod = new String(payment.getMethod());
        
        paymentRepository.setStatus("SUCCESS");

        assertEquals(payment.getStatus(), "SUCCESS");
        assertEquals(payment.getOrder().getOrderStatus(), "SUCCESS");
        assertEquals(payment.getPaymentData(), pdata);
        assertEquals(payment.getMethod(), pmethod);
    }

    @Test
    void testSetInvalidStatus() {
        String invalidStatus = "PACIL";
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.setStatus(invalidStatus));
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

        assertEquals(searchPayment, payment);
    }

    @Test
    void testGetAllPayment() {
        int numberOfPayment;
        
        List<Product> allpayment = productRepository.getAllPayment();

        numberOfPayment = allpayment.size();
        assertEquals(numberOfPayment, 2);
    }
}