package manager;

import handle.CartsHandle;

import java.util.Scanner;

public class CartsManager {
    private final Scanner scanner;
    private final CartsHandle cartsHandle;

    public CartsManager() {
        this.scanner = new Scanner(System.in);
        this.cartsHandle = new CartsHandle();
    }

    public void cartManager() {
        while (true) {
            System.out.println("================================================");
            System.out.println("                  GIỎ HÀNG                      ");
            System.out.println("================================================");
            System.out.println("1. Hiển thị giỏ hàng");
            System.out.println("2. Thay đổi số lượng sản phẩm");
            System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("4. Đặt hàng");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            int chose = scanner.nextInt();
            scanner.nextLine();

            switch (chose) {
                case 1:
                    System.out.println(cartsHandle.getAll());
                    break;
                case 2:
                    System.out.println(cartsHandle.changeAmount());
                    break;
                case 3:
                    System.out.println(cartsHandle.remove());
                    break;
                case 4:
                    System.out.println(cartsHandle.order());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số từ 1 đến 5.");
            }
        }
    }
}
