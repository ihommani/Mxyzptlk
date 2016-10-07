package model;

/**
 * Created on 06/10/16.<br/>
 *
 * Spending category model
 */
public class Category {

    private final int id;
    private final String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
