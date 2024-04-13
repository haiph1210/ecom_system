package entities;

import utils.CurrencyUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Carts extends BaseEntity {
    private List<Product> products = new ArrayList<>();
    private Integer amount;
    private BigDecimal totalPrice;

    public Carts() {
        this.products = new ArrayList<>();
        this.amount = 0;
    }

    public Carts(List<Product> products, Integer amount) {
        this.products = !Objects.isNull(products) ? products : new ArrayList<>();
        this.amount = !Objects.isNull(amount) ? amount : 0;
        this.totalPrice = !Objects.isNull(products)
                ? products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(amount))
                : BigDecimal.ZERO;
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
        outputBuilder.append("  Số lượng: ").append(amount).append("\n");
        outputBuilder.append("  Tổng tiền: ").append(CurrencyUtils.formatCurrencyVietnam(totalPrice)).append("\n");
        outputBuilder.append("  Sản phẩm trong giỏ hàng: ").append(!Objects.isNull(products)
                ? products.stream().map(Product::output).collect(Collectors.toList())
                : "").append("\n");
        return outputBuilder.toString();
    }
}
