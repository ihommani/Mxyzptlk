package model;

import java.util.List;

/**
 * Created on 07/10/16.<br/>
 * <p>
 * Model of the output format
 */
public class BankInOutput {

    private List<Item> summary;

    public BankInOutput(List<? extends Item> summary) {
        this.summary = (List<Item>) summary;
    }

    public static class Item{
        private final int id;
        private final String name;

        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class SumItem extends Item{

        private final double total;

        public SumItem(int id, String name, double total) {
            super(id, name);
            this.total = total;
        }

        public int getId() {
            return super.id;
        }

        public String getName() {
            return super.name;
        }

        public double getTotal() {
            return total;
        }
    }

    public static class AverageItem extends Item{
        private final double average;

        public AverageItem(int id, String name, double average) {
            super(id, name);
            this.average = average;
        }

        public int getId() {
            return super.id;
        }

        public String getName() {
            return super.name;
        }

        public double getAverage() {
            return average;
        }
    }
}
