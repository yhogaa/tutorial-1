package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}