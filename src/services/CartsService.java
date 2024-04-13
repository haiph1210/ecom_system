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
        initData();
    }

    private void initData() {
        List<Product> product1 = List.of(productService.getAll("CREATE","DESC").get(0));
        List<Product> product2 = List.of(productService.getAll("PRICE","DESC").get(0));
        List<Product> product3 = List.of(productService.getAll("PRICE","ASC").get(0));
        Carts carts1 = new Carts(product1, 10);
        Carts carts2 = new Carts(product2, 10);
        Carts carts3 = new Carts(product3, 10);
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
