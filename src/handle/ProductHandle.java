package handle;

import entities.Product;
import services.CartsService;
import services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductHandle {
    Scanner scanner = new Scanner(System.in);
    private final ProductService productService;

    private final CartsService cartsService;

    public ProductHandle() {
        this.productService = new ProductService();
        this.cartsService = new CartsService();
    }

    public List<String> getAllByCreate() {
        return productService.getAll("CREATE", "desc").stream().map(Product::output).collect(Collectors.toList());
    }

    public List<String> getAllByPrice() {
        return productService.getAll("PRICE", "DESC").stream().map(Product::output).collect(Collectors.toList());
    }

    public void addProduct() {
        System.out.println("Nhập số sản phẩm muốn thêm:");
        int count = scanner.nextInt();
        this.productService.addProducts(count);
    }

    public void changeActiveStatusByCode() {
        System.out.println("Nhập code muốn tìm kiếm:");
        String code = scanner.nextLine();
        this.productService.changeStatusByCode(code, true);
    }

    public void changeInActiveStatusByCode() {
        System.out.println("Nhập code muốn tìm kiếm:");
        String code = scanner.nextLine();
        this.productService.changeStatusByCode(code, false);
    }

    public List<String> getProductByName() {
        System.out.println("Nhập tên sản phẩm muốn tìm kiếm:");
        String name = scanner.nextLine();
        return this.productService.findByName(name).stream().map(Product::output).collect(Collectors.toList());
    }

    public void removeProduct() {
        System.out.println("Nhập ID sản phẩm muốn xóa:");
        Long id = scanner.nextLong();
        scanner.next();
        this.productService.remove(id);
    }

    public String addToCart() {
        System.out.println("Nhập ID đưa vào giỏ hàng:");
        Long id = scanner.nextLong();
        System.out.println("Nhập số lượng:");
        Integer amount = scanner.nextInt();
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return cartsService.addProduct(product.get(),amount).output();
        } else {
            return "ID không tồn tại hoặc sản phẩm chưa hoạt động";
        }
    }
}



