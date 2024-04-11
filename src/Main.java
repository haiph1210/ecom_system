import entities.Category;
import entities.User;

public class Main {
    public static void main(String[] args) {
        Category category = new Category();
        category.input();
        System.out.println("category.output() = " + category.output());
    }
}