package manager;

import handle.ProductHandle;

import java.util.Scanner;

public class ProductManager {
    private final Scanner scanner;
    private final ProductHandle productHandle;

    public ProductManager() {
        this.scanner = new Scanner(System.in);
        this.productHandle = new ProductHandle();
    }

    public void productMangerAdmin() {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ SẢN PHẨM                    ");
            System.out.println("================================================");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Chỉnh sửa thông tin sản phẩm");
            System.out.println("4. Ẩn sản phẩm theo mã sản phẩm");
            System.out.println("5. Ẩn nhiều sản phẩm trong danh sách nhập vào");
            System.out.println("6. Tìm kiếm sản phẩm theo tên");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println(productHandle.getAllByCreate());
                    break;
                case 2:
                    productHandle.addProduct();
                    break;
                case 3:
//                    productHandle.getProductByName();
                    break;
                case 4:
                case 5:
                    productHandle.changeInActiveStatusByCode();
                    break;
                case 6:
                    System.out.println(productHandle.getProductByName());
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }

    public void productMangerUser() {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ SẢN PHẨM                    ");
            System.out.println("================================================");
            System.out.println("1. Thêm vào giỏ hàng");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println(productHandle.addToCart());
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }
}
