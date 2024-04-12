package handle;

import entities.Category;
import services.CategoryService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CategoryHandle {
    private final CategoryService categoryService;
    private final Scanner scanner;

    public CategoryHandle() {
        this.categoryService = new CategoryService();
        this.scanner = new Scanner(System.in);
    }

    public List<String> getAll() {
        return categoryService.getAll("CREATE", "DESC").stream().map(Category::output).collect(Collectors.toList());
    }

    public String create() {
        return categoryService.addCategory().output();
    }

    public List<String> findByName() {
        System.out.println("Nhập tên tìm kiếm: ");
        String name = scanner.nextLine();
        return categoryService.findByName(name).stream().map(Category::output).collect(Collectors.toList());
    }

    public String update() {
        System.out.println("Nhập ID cần thay đổi: ");
        Long id = scanner.nextLong();
        List<Category> categories = categoryService.findById(id);
        if (!Objects.isNull(categories) && categories.size() > 0) {
            Category category = categories.get(0);
            return categoryService.update(category);
        }
        return "update không thành công";
    }

    public String dissableByCode() {
        System.out.println("Nhập mã danh mục cần ẩn: ");
        String code = scanner.nextLine();
        return categoryService.changeStatusRecordByCode(code, false);
    }
}
