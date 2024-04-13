package front_end;

import entities.User;
import manager.*;
import services.UserService;

import java.util.Scanner;

public class Screen {
    private final Scanner scanner;
    private final CartsManager cartsManager;
    private final CategoryManager categoryManager;
    private final OrderManager orderManager;
    private final ProductManager productManager;
    private final UserManager userManager;
    private final UserService authenService;

    public Screen() {
        this.cartsManager = new CartsManager();
        this.categoryManager = new CategoryManager();
        this.orderManager = new OrderManager();
        this.productManager = new ProductManager();
        this.userManager = new UserManager();
        this.scanner = new Scanner(System.in);
        this.authenService = new UserService();
    }

    public void auth() {
        while (true) {
            System.out.println("================================================");
            System.out.println("                TRANG CHỦ                       ");
            System.out.println("================================================");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("================================================");
                    System.out.println("                  ĐĂNG KÝ                      ");
                    System.out.println("================================================");
                    System.out.println(authenService.createUser());
                    break;
                case 2:
                    System.out.println("================================================");
                    System.out.println("                  ĐĂNG NHẬP                     ");
                    System.out.println("================================================");
                    System.out.print("Nhập tên đăng nhập: ");
                    String username = scanner.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String password = scanner.nextLine();
                    User user = authenService.login(username, password);
                    if (user != null ) {
                        if (user.getRole().equals("USER")) {
                            this.showUserScreen(user.getId());
                        } else {
                            this.showAdminScreen();
                        }
                    } else {
                        break;
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 3.");
            }
        }
    }

    public void showAdminScreen() {
        while (true) {
            System.out.println("======================================");
            System.out.println("            TRANG CHỦ                 ");
            System.out.println("======================================");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Quản lý danh mục");
            System.out.println("3. Quản lý sản phẩm");
            System.out.println("4. Quản lý hóa đơn");
            System.out.println("0. Đăng xuất");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userManager.userManagerAdmin();
                    break;
                case 2:
                    categoryManager.categoryManagerAdmin();
                    break;
                case 3:
                    productManager.productMangerAdmin();
                    break;
                case 4:
                    orderManager.orderManagerAdmin();
                    break;
                case 0:
                    System.out.println("Đăng xuất.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }

    public void showUserScreen(Long id) {
        while (true) {
            System.out.println("======================================");
            System.out.println("            TRANG CHỦ                 ");
            System.out.println("======================================");
            System.out.println("1. Trang chủ");
            System.out.println("2. Chi tiết sản phẩm");
            System.out.println("3. Giỏ hàng");
            System.out.println("4. Trang liên hệ");
            System.out.println("5. Trang thông tin cá nhân");
            System.out.println("6. Lịch sử đơn hàng");
            System.out.println("0. Đăng xuất");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    break;
                case 2:
                    productManager.productMangerUser();
                    break;
                case 3:
                    cartsManager.cartManager();
                    break;
                case 4:
                    userManager.contacUs();
                    break;
                case 5:
                    userManager.userManager(id);
                    break;
                case 6:
                    System.out.println("Hệ thống đang nâng cấp tính năng này");
                    break;
                case 0:
                    System.out.println("Đăng xuất.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }
}
