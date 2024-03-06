package id.ac.ui.cs.advprog.eshop.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentTest {
    private List<Product> products;
    private Order order;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());
        Order order2 = new Order("24652556-012a-4c07-b546-54eb1396d79a", this.products, 170856000L, "Safira Sudrajat 2", OrderStatus.SUCCESS.getValue());
        this.orders.add(order1);
        this.orders.add(order2);

        this.order = order1;
    }

    // Main Payment
    @Test
    void testDuplicatePayment() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);

        assertSame(this.orders.getFirst(), payment.getOrder());
    }

    @Test
    void testPaymentInvalidPaymentMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", "BISMILLAH", this.orders.getFirst(), paymentData);
        });
    }

    @Test
    void testRejectedPaymentEmptyData() {
        Map<String, String> paymentData = new HashMap<>();

        Payment voucherPayment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);
        assertEquals("REJECTED", voucherPayment.getStatus());

        Payment codPayment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), null, paymentData);
        assertEquals("REJECTED", codPayment.getStatus());
    }

    @Test
    public void testUpdateStatusWithValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);
        payment.updateStatus();

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testUpdateStatusWithMissingVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.updateStatus();
        });

        assertEquals("Invalid payment data for current method", exception.getMessage());
    }

    @Test
    public void testUpdateStatusWithValidCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "10");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), null, paymentData);

        payment.updateStatus();

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testUpdateStatusWithMissingCashOnDeliveryData() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), null, paymentData);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.updateStatus();
        });

        assertEquals("Invalid payment data for current method", exception.getMessage());
    }

    // Voucher Code
    @Test
    void testSuccessValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeWrongPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "EHEHE1234ABC5678");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot8CharNumerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567A");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    // Cash on Delivery
    @Test
    void testSuccessValidCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan-jalan");
        paymentData.put("deliveryFee", "17500");

        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedCashOnDeliveryEmptyAddress() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("deliveryFee", "17500");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedCashOnDeliveryEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan-jalan");
        Payment payment = new Payment("1994cddb-6f3b-40ca-aed1-eba78db32295", PaymentMethod.CASH_ON_DELIVERY.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

}