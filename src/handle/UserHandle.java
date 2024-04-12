package handle;

import entities.User;
import services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserHandle {
    private final Scanner scanner;
    private final UserService userService;

    public UserHandle() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
    }

    public String registerUser(String role) {
        String resp = "";
        switch (role) {
            case "ADMIN":
                userService.createAdmin();
                resp = "Bạn đã tạo thành công user admin";
                break;
            case "USER":
                userService.createAdmin();
                resp = "Bạn đã tạo thành công user";
                break;
            default:
                resp = ("bạn đã chọn sai role");
        }
        return resp;
    }

    public List<String> findAll() {
        return userService.getAll("FULLNAME", "DESC").stream().map(User::output).collect(Collectors.toList());
    }

    public List<String> getUserByFullName() {
        System.out.println("Nhập tên khách hàng cần tìm kiếm: ");
        String fullName = scanner.nextLine();
        return userService.findByFullName(fullName).stream().map(User::output).collect(Collectors.toList());
    }

    public String lockOrUnlockUser() {
        System.out.println("Nhập ID khách hàng cần lock: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Lock/Unlock: (LOCK||UNLOCK)");
        String lockOrUnlock = scanner.nextLine();
        boolean isLock = false;
        switch (lockOrUnlock) {
            case "LOCK":
                isLock = false;
                break;
            case "UNLOCK":
                isLock = true;
                break;
            default:
                return "Nhập sai (LOCK||UNLOCK)";
        }
        userService.changeStatus(id, isLock);
        return "lock||unlock user thành công";
    }

    public String unLockUser() {
        System.out.println("Nhập ID khách hàng cần unLock: ");
        Long id = scanner.nextLong();
        userService.changeStatus(id, true);
        return "unLock user thành công";
    }

    public String resetPassword() {
        System.out.println("Nhập user: ");
        String username = scanner.nextLine();
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String phone = scanner.nextLine();
        System.out.println("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();
        return userService.changePassword(username, email, phone, newPassword);
    }

    public String getProfile(Long id) {
        return userService.findById(id).output();
    }

    public String update(Long id) {
        User user = userService.findById(id);
        return userService.updateUser(user);
    }
}
