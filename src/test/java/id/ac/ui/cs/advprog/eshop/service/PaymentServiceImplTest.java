package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.enums.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
// import id.ac.ui.cs.advprog.eshop.model.service.PaymentServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    public PaymentServiceImpl paymentServiceImpl;
    
    @Mock
    public PaymentRepository paymentRepository;

    List<Payment> payments;
    Payment payment;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        List<Product> products1 = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("product1");
        product1.setProductQuantity(10);
        products1.add(product1);

        Map<String, String> paymentData = new HashMap<String, String>();
        
        paymentData.put("bank_name", "BCA");
        paymentData.put("referenceCode", "BCARAMAH");

        Order order = new Order(UUID.randomUUID().toString(), products1, (long) 1741590554, "Adam" );
        payment = new Payment(UUID.randomUUID().toString(), order, PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        paymentRepository.payments.add(payment);

        payments.add(payment);
    }
    @Test
    void testCreatePayment() {
        String paymentId = UUID.randomUUID().toString();
        payment.setId(paymentId);

        Payment createPayment = paymentServiceImpl.create(payment);

        assertEquals(payment.getOrder(), createPayment.getOrder());
        assertEquals(payment.getId(), createPayment.getId());
        assertEquals(payment.getMethod(), createPayment.getMethod());
        assertEquals(payment.getStatus(), createPayment.getStatus());
        assertEquals(payment.getPaymentData(), createPayment.getPaymentData());
    }

    @Test
    void testUpdatePayment() {
        paymentServiceImpl.update(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getOrderStatus());
    }


    @Test
    void testFindPaymentById() {
        Payment findPayment = paymentServiceImpl.findById(payment.getId());

        assertEquals(findPayment, payment);
    }

    @Test
    void testFindPaymentByIdNotExist() {
        String randomId = UUID.randomUUID().toString();

        Payment findPayment = paymentServiceImpl.findById(randomId);

        assertNull(findPayment);
    }

    @Test
    void testFindAllPayment() {
        List<Payment> allPayment = paymentsServiceImpl.findAll();

        assertEquals(allPayment.size(), 1);
    }


}