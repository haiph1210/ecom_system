package handle;

import entities.Order;
import enums.OrderStatus;
import services.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderHandle {
    private final Scanner scanner;
    private final OrderService orderService;

    public OrderHandle() {
        this.scanner = new Scanner(System.in);
        this.orderService = new OrderService();
    }

    public List<String> getAllByOrderPending() {
        return orderService.getAllOrder(OrderStatus.P).stream().map(Order::output).collect(Collectors.toList());

    }

    public List<String> getAllByOrderApproved() {
        return orderService.getAllOrder(OrderStatus.D).stream().map(Order::output).collect(Collectors.toList());

    }

    public List<String> getAllByOrderDeliver() {
        return orderService.getAllOrder(OrderStatus.A).stream().map(Order::output).collect(Collectors.toList());
    }

    public List<String> getAllByOrderReject() {
        return orderService.getAllOrder(OrderStatus.R).stream().map(Order::output).collect(Collectors.toList());
    }

    public String approved() {
        System.out.println("Nhập Id cần duyệt: ");
        Long id = scanner.nextLong();
        return orderService.approved(id);
    }

    public String reject() {
        System.out.println("Nhập Id hủy: ");
        Long id = scanner.nextLong();
        return orderService.reject(id);
    }

//    public String update() {
//        System.out.println("Nhập Id cập nhập: ");
//        Long id = scanner.nextLong();
//        Optional<Order> order = orderService.findById(id);
//        if (order.isPresent()) {
//            return orderService.
//
//        }
//    }
}
