package manager;

import handle.OrderHandle;

import java.util.Scanner;

public class OrderManager {
    private final Scanner scanner;
    private final OrderHandle orderHandle;

    public OrderManager() {
        this.scanner = new Scanner(System.in);
        this.orderHandle = new OrderHandle();
    }

    public void orderManagerAdmin() {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ HÓA ĐƠN                    ");
            System.out.println("================================================");
            System.out.println("1. Hiển thị danh sách hóa đơn chưa xác nhận");
            System.out.println("2. Hiển thị danh sách hóa đơn đã xác nhận");
            System.out.println("3. Hiển thị danh sách hóa đơn đã giao hàng");
            System.out.println("4. Hiển thị danh sách hóa đơn trả hàng");
            System.out.println("5. Xác nhận hóa đơn đang chờ");
            System.out.println("6. Hủy một đơn hàng");
            System.out.println("7. Cập nhật đơn hàng đang giao");
            System.out.println("8. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choose = scanner.nextInt();
            scanner.nextLine();

            switch (choose) {
                case 1:
                    System.out.println(orderHandle.getAllByOrderPending());
                    break;
                case 2:
                    System.out.println(orderHandle.getAllByOrderApproved());
                    break;
                case 3:
                    System.out.println(orderHandle.getAllByOrderDeliver());
                    break;
                case 4:
                    System.out.println(orderHandle.getAllByOrderReject());
                    break;
                case 5:
                case 7:
                    System.out.println(orderHandle.approved());
                    break;
                case 6:
                    System.out.println(orderHandle.reject());
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số từ 1 đến 8.");
            }
        }
    }
}
