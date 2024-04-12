package manager;

import handle.CategoryHandle;

import java.util.Scanner;

public class CategoryManager {
    private final Scanner scanner;
    private final CategoryHandle categoryHandle;

    public CategoryManager() {
        this.scanner = new Scanner(System.in);
        this.categoryHandle = new CategoryHandle();
    }

    public void categoryManagerAdmin() {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ DANH MỤC                    ");
            System.out.println("================================================");
            System.out.println("1. Hiển thị danh sách danh mục");
            System.out.println("2. Tạo mới danh mục");
            System.out.println("3. Tìm kiếm danh mục theo tên");
            System.out.println("4. Chỉnh sửa thông tin danh mục");
            System.out.println("5. Ẩn danh mục theo mã danh mục ");
            System.out.println("6. Ẩn nhiều danh mục trong danh sách nhập vào");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(categoryHandle.getAll());
                    break;
                case 2:
                    System.out.println(categoryHandle.create());
                    break;
                case 3:
                    System.out.println(categoryHandle.findByName());
                    break;
                case 4:
                    System.out.println(categoryHandle.update());
                    break;
                case 5:
                case 6:
                    System.out.println(categoryHandle.dissableByCode());
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        }
    }
}

