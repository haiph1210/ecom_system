package services;

import entities.Category;
import entities.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final Scanner scanner;
    private final List<Product> products;
    private final CategoryService categoryService;

    public ProductService(Scanner scanner,
                          List<Product> products,
                          CategoryService categoryService) {
        this.scanner = scanner;
        this.products = products;
        this.categoryService = categoryService;
    }

    public List<Product> getAll(String sortBy, String sortOrder) {
        Comparator<Product> comparator = null;
        boolean isDesc = sortOrder.equalsIgnoreCase("desc");

        switch (sortBy.toLowerCase()) {
            case "CREATE" -> comparator = Comparator.comparing(Product::getCreateDate);
            case "PRICE" -> comparator = Comparator.comparing(Product::getPrice);
            default -> {
                System.out.println("Lựa chọn không hợp lệ. Danh sách sẽ không được sắp xếp.");
                return products;
            }
        }

        return products.stream()
                .filter(product -> product.getActive() && product.getCategory().getActive())
                .sorted(isDesc ? comparator.reversed() : comparator)
                .collect(Collectors.toList());
    }

    public void addProducts(int count) {
        for (int i = 0; i < count; i++) {
            addProduct();
        }
    }

    private void addProduct() {
        System.out.println("Nhập ID của danh mục: ");
        Long idCate = scanner.nextLong();
        List<Category> categories = categoryService.findById(idCate);
        Category category;
        if (!Objects.isNull(categories) && categories.size() > 0) {
            category = categories.get(0);
        } else {
            System.out.println("Không tìm thấy bản ghi với ID danh mục: " + idCate);
            return;
        }
        Product product = new Product();
        product.input(category);
        scanner.next();
        products.add(product);
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getCode().equals(name)
                        && product.getActive()
                        && product.getCategory().getActive())
                .collect(Collectors.toList());

    }

    public List<Product> findByCode(String code) {
        return products.stream()
                .filter(product -> product.getCode().equals(code)
                        && product.getActive())
//                        && product.getCategory().getActive())
                .collect(Collectors.toList());
    }

    public List<Product> findByCodes(List<String> codes) {
        return products.stream()
                .filter(product -> codes.contains(product.getCode())
                        && product.getActive())
//                        && product.getCategory().getActive())
                .collect(Collectors.toList());
    }

    public void changeStatusByCode(String code, Boolean isActive) {
        List<Product> getProductByCode;
        if (code.contains(",")) {
            List<String> codes = List.of(code.split(","));
            getProductByCode = findByCodes(codes);
        } else {
            getProductByCode = findByCode(code);
        }
        if (!Objects.isNull(getProductByCode)
                && getProductByCode.size() > 0) {
            for (Product product : getProductByCode) {
                product.setActive(isActive);
            }
        }
    }

    public String updateProduct(Product product) {
        System.out.println("Thông tin hiện tại của sản phẩm:");
        System.out.println(product.output());
        System.out.println("Nhập thông tin mới cho sản phẩm:");
        System.out.print("Tên sản phẩm mới: ");
        String newName = scanner.nextLine();
        product.setName(newName);
        System.out.print("Giá sản phẩm mới: ");
        BigDecimal newPrice = scanner.nextBigDecimal();
        product.setPrice(newPrice);
        scanner.nextLine();
        System.out.print("Mô tả sản phẩm mới: ");
        String newDescription = scanner.nextLine();
        product.setDescription(newDescription);
        System.out.println("Thông tin sản phẩm đã được cập nhật.");
        return product.output();
    }


    public void remove(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
