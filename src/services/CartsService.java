package services;

import entities.Carts;
import entities.Product;
import enums.ShippingRule;

import java.util.*;

public class CartsService {
    private final OrderService orderService;
    private final ProductService productService;
    private final List<Carts> carts;

    public CartsService() {
        this.orderService = new OrderService();
        this.carts = new ArrayList<>();
        this.productService = new ProductService();
//        initData();
    }

    private void initData() {
        Carts carts1 = new Carts(productService.findById(1L).isPresent() ? List.of(productService.findById(1L).get()) : null, 10);
        Carts carts2 = new Carts(productService.findById(2L).isPresent() ? List.of(productService.findById(2L).get()) : null, 10);
        Carts carts3 = new Carts(productService.findById(3L).isPresent() ? List.of(productService.findById(3L).get()) : null, 10);
        carts.addAll(Arrays.asList(carts1, carts2, carts3));
    }

    public Optional<Carts> findById(Long id) {
        return carts.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public List<Carts> getAll() {
        return carts;
    }

    public Carts changeAmount(Long id, Integer amount) {
        Carts oldCarts = findById(id).orElse(null);
        if (oldCarts != null) {
            oldCarts.setAmount(amount);
            return oldCarts;
        } else {
            return null;
        }

    }

    public boolean remove(Long id) {
        return carts.removeIf(item -> item.getId().equals(id));
    }

    public String order(Long id, String address, ShippingRule shippingRule, String note) {
        String resp = "";
        Optional<Carts> oldCarts = findById(id);
        if (oldCarts.isPresent()) {
            resp = orderService.order(oldCarts.get(), address, shippingRule, note).output();
        }
        return resp;
    }

    public Carts addProduct(Product product, Integer amount) {
        Carts addCarts = new Carts(Collections.singletonList(product), amount);
        carts.add(addCarts);
        return addCarts;
    }
}
