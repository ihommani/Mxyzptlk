package model;

import java.util.List;

/**
 * Created on 07/10/16.<br/>
 * <p>
 * Model of the input provided by the bank
 */
public class BankInput {
    private List<Category> categories;
    private List<Transaction> transactions;
    private List<Budget> budgets;
    private List<Account> accounts;

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

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     *
     */
    public static class Account {
        private int id;
        private String name;
        private double balance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    /**
     * Created on 06/10/16.<br/>
     * <p>
     * Spending category model
     */
    public static class Category {

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

    /**
     * Created on 06/10/16.<br/>
     * <p>
     * Transaction model
     */
    public static class Transaction {

        private int id;
        private String description;
        private Integer category_id;
        private String date;
        private double amount;
        private int move_to_month;
        private int account_id;


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

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }
    }

    public class Budget {
        private int id;
        private int account_id;
        private int category_id;
        private double limit;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public double getLimit() {
            return limit;
        }

        public void setLimit(double limit) {
            this.limit = limit;
        }
    }
}
