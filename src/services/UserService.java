package services;

import entities.Category;
import entities.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class UserService extends BaseService {
    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        initData();
    }

    private void initData() {
        User admin = new User(1L,"admin", "admin", "admin", "admin123@gmail.com", "0999999999", "Hà Nội", "ADMIN");
        User user = new User(2L,"user01", "user01", "user01", "user01@gmail.com", "0999999998", "Hồ Chí Minh", "USER");
        this.users.addAll(Arrays.asList(admin, user));
    }

    public User login(String username, String password) {
        Optional<User> first = users.stream()
                .filter(item -> item.getUsername().equals(username.trim())
                        && item.getPassword().equals(password.trim()))
                .findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
        return null;
    }

    public List<User> getAll(String sortBy, String sortOrder) {
        Comparator<User> comparator = null;
        boolean isDesc = sortOrder.equalsIgnoreCase("desc");

        switch (sortBy.toLowerCase()) {
            case "FULLNAME" -> comparator = Comparator.comparing(User::getFullName);
            default -> {
                System.out.println("Lựa chọn không hợp lệ. Danh sách sẽ không được sắp xếp.");
                return users;
            }
        }

        return users.stream()
                .sorted(isDesc ? comparator.reversed() : comparator)
                .collect(Collectors.toList());
    }

    public String createAdmin() {
        User user = new User();
        user.input("ADMIN");
        users.add(user);
        return user.output();
    }

    public String createUser() {
        User user = new User();
        user.input("USER");
        users.add(user);
        return user.output();
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
    public User getAdmin() {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getRole().equals("ADMIN"))
                .findFirst();
        return userOptional.orElse(null);
    }

    public User findByUsernameAndEmailAndPhone(String username, String email, String phone) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getEmail().equals(email)
                        && user.getPhone().equals(phone)
                )
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

    public String changePassword(Long id, String newPassword) {
        Optional<User> userOptional = Optional.ofNullable(findById(id));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword);
            return "Thay đổi mật khẩu thành công";
        } else {
            return "Không tìm thấy người dùng, thay đổi mật khẩu thất bại";
        }
    }
    public String changePassword(String username, String email,
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
            return "Thay dổi mật khẩu thành công";
        } else {
            return ("Không tìm thấy người dùng, thay đổi mật khẩu thất bại");
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
