package front_end;

import handle.ProductHandle;

import java.util.Scanner;

public class ScreenHomeUser {
    private ProductHandle productHandle;

    public ScreenHomeUser(ProductHandle productHandle) {
        this.productHandle = productHandle;
    }

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            // Hiển thị menu trang chủ
            hienThiMenu();

            // Nhận lựa chọn từ người dùng
            int luaChon = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống

            // Xử lý lựa chọn
            switch (luaChon) {
                case 1:
                    timKiemSanPham();
                    break;
                case 2:
                    hienThiSanPhamNoiBat();
                    break;
                case 3:
                    hienThiTungNhomSanPham();
                    break;
                case 4:
                    hienThiDanhSachSanPham();
                    break;
                case 5:
                    hienThiDanhSachSapXep();
                    break;
                case 6:
                    themVaoGioHang();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    private static void hienThiMenu() {
        System.out.println("======================================");
        System.out.println("            TRANG CHỦ");
        System.out.println("======================================");
        System.out.println("1. Tìm kiếm sản phẩm");
        System.out.println("2. Hiển thị sản phẩm nổi bật");
        System.out.println("3. Hiển thị từng nhóm sản phẩm theo danh mục");
        System.out.println("4. Danh sách sản phẩm");
        System.out.println("5. Hiển thị danh sách sắp xếp theo giá tăng dần/giảm dần");
        System.out.println("6. Thêm vào giỏ hàng");
        System.out.println("7. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void timKiemSanPham() {

        System.out.println("Chức năng tìm kiếm sản phẩm đang được triển khai.");
    }

    private static void hienThiSanPhamNoiBat() {
        System.out.println("Chức năng hiển thị sản phẩm nổi bật đang được triển khai.");
    }

    private static void hienThiTungNhomSanPham() {
        System.out.println("Chức năng hiển thị từng nhóm sản phẩm theo danh mục đang được triển khai.");
    }

    private static void hienThiDanhSachSanPham() {
        System.out.println("Chức năng hiển thị danh sách sản phẩm đang được triển khai.");
    }

    private static void hienThiDanhSachSapXep() {
        System.out.println("Chức năng hiển thị danh sách sắp xếp theo giá đang được triển khai.");
    }

    private static void themVaoGioHang() {
        System.out.println("Chức năng thêm vào giỏ hàng đang được triển khai.");
    }
}
