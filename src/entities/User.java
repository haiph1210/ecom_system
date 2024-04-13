package entities;

public class User extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String role;

    private boolean isActive;

    public User() {
        super();
    }

    public User(String username, String password, String fullName, String email, String phone, String address, String role) {
        super();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.isActive = true;
    }

    public User(Long id, String username, String password, String fullName, String email, String phone, String address, String role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void input() {
        inputOverload();
    }

    public void input(String role) {
        inputOverload();
        this.role = role;
    }

    private void inputOverload() {
        System.out.println("Nhập tài khoản: ");
        this.username = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        this.password = scanner.nextLine();
        System.out.println("Nhập tên đầy đủ: ");
        this.fullName = scanner.nextLine();
        System.out.println("Nhập email: ");
        this.email = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        this.phone = scanner.nextLine();
        System.out.println("Nhập địa chỉ: ");
        this.address = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "User{" +
                " id=" + id +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                ",username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Thông tin người dùng: \n");
        outputBuilder.append(super.output());
        outputBuilder.append("  Tên tài khoản: ").append(username).append("\n");
        outputBuilder.append("  Tên khách hàng: ").append(fullName).append("\n");
        outputBuilder.append("  Email: ").append(email).append("\n");
        outputBuilder.append("  Số điện thoại: ").append(phone).append("\n");
        outputBuilder.append("  Địa chỉ: ").append(address).append("\n");
        outputBuilder.append("  Vai trò: ").append(role).append("\n");
        outputBuilder.append("  Hoạt động: ").append(isActive ? "Đang hoạt động" : "Không hoạt động").append("\n");
        return outputBuilder.toString();
    }
}
