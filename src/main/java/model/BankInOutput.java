package model;

import java.util.List;

/**
 * Created on 07/10/16.<br/>
 * <p>
 * Model of the output format
 */
public class BankInOutput {

    private final List<SumItem> summary;

    public BankInOutput(List<SumItem> summary) {
        this.summary = summary;
    }

    public static class SumItem {
        private final int id;
        private final String name;
        private final double total;

        public SumItem(int id, String name, double total) {
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

    public static class AverageItem {
        private final int id;
        private final String name;
        private final double average;

        public AverageItem(int id, String name, double average) {
            this.id = id;
            this.name = name;
            this.average = average;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getAverage() {
            return average;
        }
    }
}
