package model;

import java.util.List;

/**
 * Created on 07/10/16.<br/>
 *
 * Model of the input provided by the bank
 */
public class BankInput {
    private List<Category> categories;
    private List<Transaction> transactions;

    public BankInput() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
