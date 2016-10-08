package model;

import java.util.List;

/**
 * Created on 07/10/16.<br/>
 *
 * Model of the output format
 */
public class BankInOutput {

    private final List<Item> summary;

    public BankInOutput(List<Item> summary) {
        this.summary = summary;
    }

    public static class Item {
        private final int id;
        private final String name;
        private final double total;

        public Item(int id, String name, double total) {
            this.id = id;
            this.name = name;
            this.total = total;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getTotal() {
            return total;
        }
    }
}
