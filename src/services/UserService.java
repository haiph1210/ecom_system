package services;

import entities.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService extends BaseService {
    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> getAll(String sortBy, String sortOrder) {
        return null;
    }

    public void createAdmin() {
        User user = new User();
        user.input("ADMIN");
    }

    public void createUser() {
        User user = new User();
        user.input("USER");
    }

    public List<User> findByFullName(String fullName) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        return userOptional.orElse(null);
    }

    public void changeStatus(Long id, boolean isActive) {
        User oldUser = findById(id);
        if (!Objects.isNull(oldUser)) {
            oldUser.setActive(isActive);
        } else {
            System.out.println("Không tìm thấy bản ghi với ID:" + id);
        }
    }

    public void changePassword(String username, String email,
                               String phone, String newPassword) {
        Optional<User> oUser = users.stream()
                .filter(item -> item.isActive()
                        && item.getUsername().equals(username)
                        && item.getEmail().equals(email)
                        && item.getPhone().equals(phone))
                .findFirst();

        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setPassword(newPassword);
        } else {
            System.out.println("Không tìm thấy người dùng, thay đổi mật khẩu thất bại");
        }
    }

    public String updateUser(User user) {
        System.out.println("Thông tin hiện tại của người dùng:");
        System.out.println(user.output());
        System.out.println("Nhập thông tin mới cho người dùng:");
        System.out.print("Email mới: ");
        String email = scanner.nextLine();
        user.setEmail(email);
        System.out.print("Số điện thoại mới: ");
        String phone = scanner.nextLine();
        user.setPhone(phone);
        System.out.print("Địa chỉ mới: ");
        String address = scanner.nextLine();
        user.setAddress(address);

        System.out.println("Thông tin sản phẩm đã được cập nhật.");
        return user.output();
    }
}
