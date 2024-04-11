package services;

import entities.Category;
import entities.Product;
import handle.ProductHandle;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryService extends BaseService {
    private List<Category> categories;
    private ProductHandle productHandle;

    public CategoryService(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getAll(String sortBy, String sortOrder) {
        Comparator<Category> comparator = null;
        boolean isDesc = sortOrder.equalsIgnoreCase("desc");

        switch (sortBy.toLowerCase()) {
            case "CREATE" -> comparator = Comparator.comparing(Category::getCreateDate);
            default -> {
                System.out.println("Lựa chọn không hợp lệ. Danh sách sẽ không được sắp xếp.");
                return categories;
            }
        }

        return categories.stream()
                .filter(Category::getActive)
                .sorted(isDesc ? comparator.reversed() : comparator)
                .collect(Collectors.toList());
    }

    public void addCategory() {
        Category category = new Category();
        category.input();
        categories.add(category);
    }

    public List<Category> findByName(String name) {
        return categories
                .stream()
                .filter(item -> name.equalsIgnoreCase(item.getName())
                        && item.getActive())
                .collect(Collectors.toList());
    }

    public List<Category> findById(Long id) {
        return categories
                .stream()
                .filter(item -> id.equals(item.getId())
                        && item.getActive())
                .collect(Collectors.toList());
    }

    public List<Category> findByCode(String code) {
        return categories
                .stream()
                .filter(item -> code.equalsIgnoreCase(item.getCode()))
                .collect(Collectors.toList());
    }

    public void changeStatusRecordByCode(String code, boolean isActive) {
        List<Category> getCategoryByCode;
        if (code.contains(",")) {
            List<String> codes = List.of(code.split(","));
            getCategoryByCode = findByCodes(codes);
        } else {
            getCategoryByCode = findByCode(code);
        }
        if (!Objects.isNull(getCategoryByCode)
                && getCategoryByCode.size() > 0) {
            for (Category category : getCategoryByCode) {
                category.setActive(isActive);
            }
        }
    }

    public String update(Category category) {
        System.out.println("Thông tin hiện tại của danh mục:");
        System.out.println(category.output());
        System.out.println("Nhập thông tin mới cho sản phẩm:");
        System.out.print("Tên danh mục mới: ");
        String newName = scanner.nextLine();
        category.setName(newName);
        System.out.println("Thông tin danh mục đã được cập nhật.");
        return category.output();
    }

    private List<Category> findByCodes(List<String> codes) {
        return categories.stream()
                .filter(product -> codes.contains(product.getCode())
                        && product.getActive())
                .collect(Collectors.toList());
    }


}
