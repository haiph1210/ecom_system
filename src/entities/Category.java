package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Category extends BaseEntity {
    private String code;
    private String name;
    private Boolean isActive = true;
    private List<Product> products = new ArrayList<>();

    public Category() {
        this.products = new ArrayList<>();
    }

    public Category(String name, List<Product> products) {
        int randomNumber = random.nextInt(1000000);
        this.code = String.format("%07d", randomNumber);
        this.name = name;
        this.products = products != null ? products : new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        product.setCategory(this);
    }
    @Override
    public void input() {
        System.out.println("Nhập tên sản phẩm: ");
        this.name = scanner.nextLine();
    }

    @Override
    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Thông tin danh mục: \n");
        outputBuilder.append(super.output());
        outputBuilder.append("  Mã danh mục: ").append(code).append("\n");
        outputBuilder.append("  Tên danh mục: ").append(name).append("\n");
        outputBuilder.append("  Trạng thái hoạt động: ").append(isActive ? "Đang hoạt động" : "Không hoạt động").append("\n");
//        outputBuilder.append("  Sản phẩm của danh mục: ").append(!Objects.isNull(products) ? products.stream().map(Product::output).collect(Collectors.toList()) : "").append("\n");
        return outputBuilder.toString();
    }
}
