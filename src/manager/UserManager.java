package manager;

import entities.User;
import handle.UserHandle;

import java.util.Scanner;

public class UserManager {
    private final UserHandle userHandle;
    private final Scanner scanner;

    public UserManager() {
        this.userHandle = new UserHandle();
        this.scanner = new Scanner(System.in);
    }


    public void userManagerAdmin() {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ NGƯỜI DÙNG                  ");
            System.out.println("================================================");
            System.out.println("1. Hiển thị danh sách người dùng");
            System.out.println("2. Tìm kiếm người dùng theo tên");
            System.out.println("3. Block/Unblock tài khoản người dùng");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println(userHandle.findAll());
                    break;
                case 2:
                    System.out.println(userHandle.getUserByFullName());
                    break;
                case 3:
                    System.out.println(userHandle.lockOrUnlockUser());
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }


    public void userManager(Long id) {
        while (true) {
            System.out.println("================================================");
            System.out.println("            QUẢN LÝ NGƯỜI DÙNG                  ");
            System.out.println("================================================");
            System.out.println("1. Đổi mật khẩu");
            System.out.println("2. Hiển thị thông tin cá nhân");
            System.out.println("3. Chỉnh sửa thông tin cá nhân");
            System.out.println("0. Đăng xuất");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println(userHandle.resetPassword());
                    break;
                case 2:
                    System.out.println(userHandle.getProfile(id));
                    break;
                case 3:
                    System.out.println(userHandle.update(id));
                    break;
                case 0:
                    System.out.println("Đăng xuất.");
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ.");
                    break;
            }
        }
    }
}
