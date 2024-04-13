package services;

import entities.Category;
import entities.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final Scanner scanner;
    private final List<Product> products;
    private final CategoryService categoryService;

    public ProductService() {

        this.scanner = new Scanner(System.in);
        this.products = new ArrayList<>();
        this.categoryService = new CategoryService();
        addInitialProducts();
    }

    private void addInitialProducts() {
        Category defaultCategory = categoryService.getAll("CREATE", "DESC").get(0);

        String[] names = {"Smartphone", "Laptop", "Headphones", "T-shirt", "Sneakers", "Watch", "Book", "Toy", "Camera", "Home Decor"};
        String[] titles = {"High-end Smartphone", "Premium Laptop", "Wireless Headphones", "Cotton T-shirt", "Sporty Sneakers", "Luxury Watch", "Bestseller Book", "Educational Toy", "Professional Camera", "Decorative Lamp"};
        String[] descriptions = {"The latest flagship smartphone with advanced features.", "Powerful laptop for professional use.", "Immersive sound quality with noise cancellation.", "Comfortable and stylish cotton t-shirt.", "Durable sneakers for active lifestyle.", "Elegant watch with precision timekeeping.", "Bestselling book with captivating story.", "Educational toy for kids' development.", "High-resolution camera for photography enthusiasts.", "Unique home decor item to enhance your living space."};

        for (int i = 0; i < 10; i++) {
            String name = names[i];
            String title = titles[i];
            String description = descriptions[i];
            String dateAdded = LocalDate.now().toString();
            BigDecimal price = BigDecimal.valueOf(new Random().nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
            Product product = new Product(name, title, description, dateAdded, price, defaultCategory);
            products.add(product);
            defaultCategory.addProduct(product);
        }
    }

    public List<Product> getAll(String sortBy, String sortOrder) {
        Comparator<Product> comparator = null;
        boolean isDesc = sortOrder.equalsIgnoreCase("desc");

        switch (sortBy.toUpperCase()) {
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
        products.add(product);
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id)
                        && product.getActive()
                        && product.getCategory().getActive())
                .findFirst();
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name)
                        && product.getActive()
                        && product.getCategory().getActive()
                )
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

    public String changeStatusByCode(String code, Boolean isActive) {
        String resp = "";
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
            resp = "Cập nhật trạng thái thành công";
        } else {
            resp = "Cập nhật trạng thái không thành công,không tồn tại code bạn tìm kiếm";
        }
        return resp;
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
