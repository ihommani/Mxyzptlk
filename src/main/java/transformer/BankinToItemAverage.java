package transformer;

import model.BankInOutput;
import model.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankinToItemAverage {

    private final Month month;

    private final BankinToItemAverage.BankInputToBankIn bankInputToBankIn = new BankinToItemAverage.BankInputToBankIn();

    public BankinToItemAverage(Month month) {
        this.month = month;
    }

    public List<BankInOutput.AverageItem> transform(Map<Integer, String> categoryNameById, List<Transaction> transactions) {
        return bankInputToBankIn.apply(categoryNameById, transactions);
    }

    class BankInputToBankIn implements BiFunction<Map<Integer, String>, List<Transaction>, List<BankInOutput.AverageItem>> {

        @Override
        public List<BankInOutput.AverageItem> apply(Map<Integer, String> categoryNameById, List<Transaction> transactions) {

            return categoryNameById.entrySet().stream()
                    .map(entry -> {
                        double averageSpendinOnCategory = transactions.stream()
                                .map(transaction -> {
                                    transaction.setDate(LocalDate.parse(transaction.getDate()).plusMonths(transaction.getMove_to_month()).toString());
                                    return transaction;
                                })
                                .filter(transaction -> entry.getKey().equals(transaction.getCategory_id()))
                                .filter(transaction -> {
                                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                                    return transactionDate.isBefore(LocalDate.of(transactionDate.getYear(), month, transactionDate.getDayOfMonth()));
                                })
                                .filter(transaction -> {
                                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                                    LocalDate startDate = transactionDate.minusMonths(4);
                                    return transactionDate.isBefore(LocalDate.of(transactionDate.getYear(), month, transactionDate.getDayOfMonth())) && transactionDate.isAfter(startDate);
                                })
                                .mapToDouble(Transaction::getAmount)
                                .average()
                                .orElse(0);
                        return new BankInOutput.AverageItem(entry.getKey(), entry.getValue(), averageSpendinOnCategory);
                    })
                    .collect(Collectors.toList());
        }
    }
}
