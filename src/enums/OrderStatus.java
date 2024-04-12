package enums;

public enum OrderStatus {
    P("CHỜ DUYỆT"),
    D("ĐANG GIAO"),
    A("ĐÃ GIAO"),
    R("ĐÃ HỦY"),
    ;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OrderStatus(String value) {
        this.value = value;
    }
}
