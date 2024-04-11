package entities;

import enums.ShippingRule;
import enums.Status;
import utils.CurrencyUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Order extends BaseEntity {
    Random random = new Random();
    private String code;
    private Product product;
    private Carts carts;
    private BigDecimal totalPrice;
    private Status status = Status.P;
    private String address;
    private ShippingRule shippingRule;
    private String note;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Carts getCarts() {
        return carts;
    }

    public void setCarts(Carts carts) {
        this.carts = carts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShippingRule getShippingRule() {
        return shippingRule;
    }

    public void setShippingRule(ShippingRule shippingRule) {
        this.shippingRule = shippingRule;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Order(Product product, Carts carts, String address, ShippingRule shippingRule, String note) {
        int randomNumber = random.nextInt(1000000);
        this.code = String.format("%07d", randomNumber);
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (!Objects.isNull(product)) {
            this.product = product;
            totalPrice = product.getPrice();
        }
        if (!Objects.isNull(carts)) {
            this.carts = carts;
            totalPrice = product.getPrice();
        }
        this.address = address;
        BigDecimal shippingFee = BigDecimal.ZERO;
        if (!Objects.isNull(shippingRule)) {
            this.shippingRule = shippingRule;
            shippingFee = shippingRule.getShippingFee();
        }
        this.totalPrice = totalPrice.add(shippingFee);
        this.note = note;
    }

    @Override
    public void input() {

    }

    @Override
    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Thông tin đơn hàng: \n");
        outputBuilder.append(super.output());
        outputBuilder.append("  Mã đơn hàng: ").append(code).append("\n");
        outputBuilder.append("  Sản phẩm: ").append(product).append("\n");
        outputBuilder.append("  Giỏ hàng: ").append(carts.getProducts().toString()).append("\n");
        outputBuilder.append("  Trạng thái đơn hàng: ").append(!Objects.isNull(status) ? status.getValue() : null).append("\n");
        outputBuilder.append("  Đơn vị ship hàng: ").append(!Objects.isNull(shippingRule) ? shippingRule.getValue() : null).append("\n");
        outputBuilder.append("  Địa chỉ: ").append(address).append("\n");
        outputBuilder.append("  Ghi chú: ").append(note).append("\n");
        outputBuilder.append("  Tổng đơn hàng: ").append(CurrencyUtils.formatCurrencyVietnam(totalPrice)).append("\n");
        return outputBuilder.toString();
    }
}
