package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Payment payment;
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("1b709a33-4da3-4cbd-abd1-dc4dd3d855eb");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("c564148a-a11f-4865-9744-60ade538c37b", products,
                System.currentTimeMillis(), "Bambang", OrderStatus.WAITING_PAYMENT.getValue());

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", PaymentStatus.SUCCESS.getValue(), paymentData);
    }

    @Test
    void testPaymentCreation() {
        assertEquals("1424f2b7-2af2-4b6e-a43b-a25cb252e958", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetOrder() {
        List<Product> newProducts = new ArrayList<>();
        Product newProduct = new Product();
        newProduct.setProductId("rinso-rinso-id");
        newProduct.setProductName("rinso");
        newProduct.setProductQuantity(1);
        newProducts.add(newProduct);

        Order newOrder = new Order("rinso-rinso-id", newProducts,
                System.currentTimeMillis(), "Bambang", OrderStatus.WAITING_PAYMENT.getValue());

        payment.setOrder(newOrder);
        assertEquals(newOrder, payment.getOrder());
        assertNotEquals(order, payment.getOrder());
    }

    @Test
    void testSetPaymentMethod() {
        payment.setMethod("TRANSFER_BANK");
        assertEquals("TRANSFER_BANK", payment.getMethod());
    }

    @Test
    void testSetWrongPaymentMethod() {
        String paymentMethod = "EWALLET";
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod(paymentMethod));
    }

    @Test
    void testSetStatus() {
        payment.setStatus("REJECTED");
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentData() {
        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("voucherCode", "ESHOP87654321XYZ");

        payment.setPaymentData(newPaymentData);
        assertEquals(newPaymentData, payment.getPaymentData());
        assertNotEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testInvalidVoucherMoreThan16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABCD");
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(paymentData));
    }

    @Test
    void testInvalidVoucherLessThan16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678AB");
        assertThrows(IllegalArgumentException.class,() -> payment.setPaymentData(paymentData));
    }

    @Test
    void testInvalidVoucherStartWithoutESHOP() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "PACIL12345678ABC");
        assertThrows(IllegalArgumentException.class,() -> payment.setPaymentData(paymentData));
    }

    @Test
    void testInvalidVoucherEmpty() {
        Map<String, String> emptyVoucherData = new HashMap<>();
        emptyVoucherData.put("voucherCode", "");

        assertThrows(IllegalArgumentException.class,() -> payment.setPaymentData(emptyVoucherData));
        assertNotEquals(payment.getPaymentData().get("voucherCode"), "");
    }
}