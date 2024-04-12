package handle;

import entities.Carts;
import enums.ShippingRule;
import services.CartsService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartsHandle {
    private Scanner scanner;
    private CartsService cartsService;

    public CartsHandle() {
        this.scanner = new Scanner(System.in);
        this.cartsService = new CartsService();
    }

    public List<String> getAll() {
        return cartsService.getAll().stream().map(Carts::output).collect(Collectors.toList());
    }

    public String changeAmount() {
        System.out.println("Nhập id giỏ hàng muốn thêm số lượng: ");
        Long id = scanner.nextLong();
        scanner.next();
        System.out.println("Nhập số lượng chỉnh sửa: ");
        Integer amount = scanner.nextInt();
        scanner.next();
        return cartsService.changeAmount(id, amount).output();
    }

    public String remove() {
        System.out.println("Nhập id giỏ hàng muốn xóa: ");
        Long id = scanner.nextLong();
        scanner.next();
        return cartsService.remove(id) ? "Xóa giỏ hàng thành công" : "Xóa giỏ hàng thất bại";
    }

    public String order() {
        System.out.println("Nhập id giỏ hàng thanh toán: ");
        Long id = scanner.nextLong();
        scanner.next();
        System.out.println("Nhập địa chỉ nhận hàng: ");
        String address = scanner.nextLine();
        System.out.println("Nhập ghi chú: ");
        String note = scanner.nextLine();
        System.out.println("Nhập phương thức vận chuyển: (Ship thường: OFTEN || Ship nhanh: FAST)");
        String shippingRule = scanner.nextLine();
        ShippingRule shipping;
        switch (shippingRule) {
            case "OFTEN":
                shipping = ShippingRule.OFTEN;
                break;
            case "FAST":
                shipping = ShippingRule.FAST;
                break;
            default:
                return "Nhập sai phương thức vận chuyển";
        }
        return cartsService.order(id, address, shipping, note);
    }
}
