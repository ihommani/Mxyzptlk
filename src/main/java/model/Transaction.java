package model;

/**
 * Created on 06/10/16.<br/>
 *
 * Transaction model
 */
public class Transaction {

    private int id;
    private String description;
    private Integer category_id;
    private String date;
    private double amount;
    private int move_to_month;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMove_to_month() {
        return move_to_month;
    }

    public void setMove_to_month(int move_to_month) {
        this.move_to_month = move_to_month;
    }
}
