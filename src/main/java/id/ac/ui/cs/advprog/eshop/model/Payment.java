package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
    }

    public void setStatus(String status) {
    }

    public Order getOrder() {
        return null;
    }
}