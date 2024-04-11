package handle;

import entities.Product;
import services.ProductService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductHandle {
    Scanner scanner = new Scanner(System.in);
    private ProductService productService;

    public ProductHandle(ProductService productService) {
        this.productService = productService;
    }

    public List<String> getAllByCreate() {
        return productService.getAll("CREATE", "DESC").stream().map(Product::output).collect(Collectors.toList());
    }

    public List<String> getAllByPrice() {
        return productService.getAll("PRICE", "DESC").stream().map(Product::output).collect(Collectors.toList());
    }

    public void addProduct() {
        System.out.println("Nhập số sản phẩm muốn thêm:");
        int count = scanner.nextInt();
        scanner.next();
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
}



