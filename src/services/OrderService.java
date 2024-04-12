package services;

import entities.Carts;
import entities.Order;
import enums.OrderStatus;
import enums.ShippingRule;
import enums.Status;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService extends BaseService {
    private final List<Order> orders;
    private final ProductService productService;

    public OrderService() {
        this.orders = new ArrayList<>();
        this.productService = new ProductService();
        initData();
    }

    private void initData() {
        Order orderProduct = new Order(productService.findById(1L).isPresent() ? productService.findById(1L).get() : null, null, "Ninh Bình", ShippingRule.FAST, "Nhà Hàng");
        orders.add(orderProduct);
    }

    public Optional<Order> findById(Long id) {
        return orders.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public List<Order> getAllOrder(OrderStatus orderStatus) {
        return orders
                .stream()
                .filter(item -> item.getOrderStatus().equals(orderStatus))
                .collect(Collectors.toList());
    }

//    public void approvedDeliver(Long id) {
//        Order order = findById(id).orElse(null);
//        if (!Objects.isNull(order)) {
//            order.setOrderStatus(OrderStatus.D);
//        }
//    }

    public String approved(Long id) {
        String resp = "";
        Order order = findById(id).orElse(null);
        if (!Objects.isNull(order)) {
            order.setStatus(Status.A);
            order.setOrderStatus(OrderStatus.D);
            resp = "Duyệt thành công";
        } else {
            resp = "Duyệt không thành công";
        }
        return resp;
    }

    public String reject(Long id) {
        String resp = "";
        Order order = findById(id).orElse(null);
        if (!Objects.isNull(order)
                && order.getOrderStatus().equals(OrderStatus.D)
                && order.getOrderStatus().equals(OrderStatus.A)) {
            order.setStatus(Status.R);
            order.setOrderStatus(OrderStatus.R);
            resp = "Hủy thành công";
        } else {
            resp = "Hủy không thành công, đơn hàng không tồn tại hoặc đã ở trạng thái giao hàng";
        }
        return resp;
    }

    public Order order(Carts carts, String address, ShippingRule shippingRule, String note) {
        Order order = new Order(null, carts, address, shippingRule, note);
        orders.add(order);
        return order;
    }
}
