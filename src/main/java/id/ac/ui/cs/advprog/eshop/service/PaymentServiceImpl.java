package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(String paymentId, Order order, String method, Map<String, String> paymentData) {
        if (paymentRepository.findById(paymentId) == null){
            Payment payment = new Payment(paymentId, method, order, paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Order currentOrder = payment.getOrder();
        if (status.equals("SUCCESS")) {
            currentOrder.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status.equals("REJECTED")) {
            currentOrder.setStatus(OrderStatus.FAILED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid status payment");
        }
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            return payment;
        } else {
            throw new NoSuchElementException("Id not found");
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }
}