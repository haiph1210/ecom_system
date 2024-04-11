package enums;

public enum Status {
    P("Chờ duyệt"),
    A("Đã duyệt"),
    R("Đã từ chối"),
    ;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Status(String value) {
        this.value = value;
    }
}
