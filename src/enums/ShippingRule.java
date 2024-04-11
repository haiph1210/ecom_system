package enums;

import java.math.BigDecimal;

public enum ShippingRule {
    OFTEN("Ship thường", BigDecimal.valueOf(20000)),
    FAST("Ship nhanh", BigDecimal.valueOf(40000));
    private String value;
    private BigDecimal shippingFee;

    public String getValue() {
        return value;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }


    public void setValue(String value) {
        this.value = value;
    }

    ShippingRule(String value, BigDecimal shippingFee) {
        this.value = value;
        this.shippingFee = shippingFee;
    }
}
