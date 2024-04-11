package entities;

import utils.CurrencyUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carts extends BaseEntity {
    private List<Product> products = new ArrayList<>();
    private Integer amount;
    private BigDecimal totalPrice;

    public Carts() {
    }

    public Carts(List<Product> products, Integer amount) {
        this.products = products;
        this.amount = amount;
        this.totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(amount));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public void input() {

    }

    @Override
    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Thông tin giỏ hàng: \n");
        outputBuilder.append(super.output());
        outputBuilder.append("  Sản phẩm trong giỏ hàng: ").append(products.toString()).append("\n");
        outputBuilder.append("  Số lượng: ").append(amount).append("\n");
        outputBuilder.append("  Tổng tiền: ").append(CurrencyUtils.formatCurrencyVietnam(totalPrice)).append("\n");
        return outputBuilder.toString();
    }
}
