package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.*;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    
    @Spy
    private PaymentRepository paymentRepository = new PaymentRepository();
    
    private Payment payment;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
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
 
    }
    
    @Test
    void testCreate() {
        Payment createdPayment = paymentService.create(payment);
        
        assertEquals(payment, createdPayment);
        assertTrue(paymentRepository.payments.contains(payment));
    }
    
    @Test
    void testFindById() {
        paymentRepository.payments.add(payment);
        String paymentId = payment.getId();
        
        
        Payment foundPayment = paymentService.findById(paymentId);
        
        verify(paymentRepository).getPayment(paymentId);
        assertEquals(payment, foundPayment);
    }
    
    @Test
    void testFindAll() {
        paymentRepository.payments.add(payment);
        
        List<Payment> actualPayments = paymentService.findAll();
        
        verify(paymentRepository).getAllPayment();
        assertTrue(actualPayments.contains(payment));
    }
    
    @Test
    void testUpdate() {
        String newStatus = "SUCCESS";
        
        paymentService.update(payment, newStatus);
        
        verify(paymentRepository).setStatus(payment, newStatus);
    }
}