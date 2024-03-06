package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;

        this.paymentData = paymentData;
        if (method.equals("VOUCHER_CODE")) {
            this.status = verifyVoucherCode();
        }
        else if (method.equals("CASH_ON_DELIVERY")) {
            this.status = verifyCashOnDelivery();
        }

        if (! PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid method");
        }
        this.method = method;

        this.order = order;
        this.paymentData = paymentData;

        if (status == null) {
            updateStatus();
        }
    }

    public void updateStatus() {
        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            if (! this.paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyVoucherCode();
        }
        else if (this.method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            if (! this.paymentData.containsKey("address") ||
                    ! this.paymentData.containsKey("deliveryFee")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyCashOnDelivery();
        }
    }

    private String verifyVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int numCount = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private String verifyCashOnDelivery() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isEmpty()) {
            return "REJECTED";
        }

        if (deliveryFee == null || deliveryFee.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }
}