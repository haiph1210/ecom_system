package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public abstract class BaseEntity implements Serializable {
    protected Random random = new Random();
    protected Scanner scanner = new Scanner(System.in);
    private static final Long serialSerializableUID = 1L;
    private static Long count = 0L;
    protected Long id;
    protected LocalDate createDate;
    protected String createBy;
    protected LocalDate updateDate;
    protected String updateBy;

    public BaseEntity() {
        this.id = ++count;
        this.createDate = LocalDate.now();
        this.createBy = "SYSTEM";
        this.updateDate = LocalDate.now();
        this.updateBy = "SYSTEM";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateDate=" + updateDate +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }

    public abstract void input();

    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append("  Id: ").append(id).append("\n");
        outputBuilder.append("  Ngày tạo: ").append(createDate).append("\n");
        outputBuilder.append("  Người tạo: ").append(createBy).append("\n");
        outputBuilder.append("  Ngày cập nhật: ").append(updateDate).append("\n");
        outputBuilder.append("  Người cập nhật: ").append(updateBy).append("\n");
        return outputBuilder.toString();
    }

}
