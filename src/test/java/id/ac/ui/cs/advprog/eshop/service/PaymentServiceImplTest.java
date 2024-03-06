package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

import java.util.*;
import java.util.UUID;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        // product
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        // order
        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products,
                1708570000L, "Safira Sudrajat");
        orders.add(order2);

        // payment
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC567A");
        Payment payment1 = new Payment("8044cdbf-167c-4432-ae5b-0ce23d750401", PaymentMethod.VOUCHER_CODE.getValue(), orders.getFirst(), paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jalan-jalan");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("d0a06bee-d457-4e62-8b30-e3c2d250dc8b", PaymentMethod.CASH_ON_DELIVERY.getValue(), orders.getFirst(), paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        UUID uuid = UUID.randomUUID();
        String paymentId = uuid.toString();
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getValue(), orders.getFirst(), payments.getFirst().getPaymentData());

        doReturn(null).when(paymentRepository).findById(paymentId);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(paymentId, orders.getFirst(), PaymentMethod.VOUCHER_CODE.getValue(), payments.getFirst().getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfNull() {
        String id = "210c253c-398b-422f-aa98-f6c447e8c277";
        Payment payment = new Payment(id, PaymentMethod.VOUCHER_CODE.getValue(), orders.get(1),  payments.getFirst().getPaymentData());

        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.addPayment(id, orders.get(1), PaymentMethod.VOUCHER_CODE.getValue(), payments.getFirst().getPaymentData()));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testSetValidStatus() {
        Payment payment = payments.get(1);

        Payment newPayment1 = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(), payment.getPaymentData());
        Payment result1 = paymentService.setStatus(newPayment1, "SUCCESS");
        assertEquals(newPayment1.getId(), result1.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result1.getOrder().getStatus());

        Payment newPayment2 = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(), payment.getPaymentData());
        Payment result2 = paymentService.setStatus(newPayment1, "REJECTED");
        assertEquals(newPayment2.getId(), result2.getId());
        assertEquals(OrderStatus.FAILED.getValue(), result1.getOrder().getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);

        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(), payment.getPaymentData());

        assertThrows(IllegalArgumentException.class, () -> {
            Payment result = paymentService.setStatus(newPayment, "HAHHH");
        });
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        String paymentId = "nonExistentId";
        when(paymentRepository.findById(paymentId)).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> {
            paymentService.getPayment(paymentId);
        });
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);
    }
}