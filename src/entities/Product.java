package entities;

import java.math.BigDecimal;
import java.util.Random;

public class Product extends BaseEntity {
    Random random = new Random();

    private String code;
    private String name;
    private String title;
    private String description;
    private String dateAdded; // ngày nhập
    private Boolean isActive = true;
    private BigDecimal price;

    private Category category;

    public Product() {
    }

    public Product(String name, String title, String description, String dateAdded, BigDecimal price, Category category) {
        int randomNumber = random.nextInt(1000000);
        this.code = String.format("%07d", randomNumber);
        this.name = name;
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.price = price;
        this.category = category;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getActive() {
        return this.isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void input() {
        inputOverloading();
        scanner.next();
    }

    public void input(Category category) {
        inputOverloading();
        this.category = category;
        scanner.next();
    }

    private void inputOverloading() {
        System.out.println("Nhập tên sản phẩm: ");
        this.name = scanner.nextLine();
        System.out.println("Nhập tiêu đề sản phẩm: ");
        this.title = scanner.nextLine();
        System.out.println("Nhập mô tả sản phẩm: ");
        this.description = scanner.nextLine();
        System.out.println("Nhập ngày nhập sản phẩm: ");
        this.dateAdded = scanner.nextLine();
        System.out.println("Nhập số tiền sản phẩm: ");
        this.price = scanner.nextBigDecimal();
    }

    @Override
    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Thông tin sản phẩm: \n");
        outputBuilder.append(super.output());
        outputBuilder.append("  Code: ").append(code).append("\n");
        outputBuilder.append("  Tên sản phẩm: ").append(name).append("\n");
        outputBuilder.append("  Tiêu đề sản phẩm: ").append(title).append("\n");
        outputBuilder.append("  Mô tả sản phẩm: ").append(description).append("\n");
        outputBuilder.append("  Ngày nhập sản phẩm: ").append(dateAdded).append("\n");
        outputBuilder.append("  Giá sản phẩm: ").append(price).append("\n");
        return outputBuilder.toString();
    }
}
