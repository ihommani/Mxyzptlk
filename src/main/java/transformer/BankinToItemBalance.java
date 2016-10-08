package transformer;

import model.BankInOutput;
import model.BankInput;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankinToItemBalance {

    private final Month month;

    private final BankInput.Account account;


    private final BankinToItemBalance.BankInputToBankIn bankInputToBankIn = new BankinToItemBalance.BankInputToBankIn();

    public BankinToItemBalance(Month month, BankInput.Account account) {
        this.month = month;
        this.account = account;
    }

    public List<BankInOutput.AverageItem> transform(Map<Integer, String> categoryNameById, List<BankInput.Transaction> transactions) {
        return bankInputToBankIn.apply(categoryNameById, transactions);
    }

    class BankInputToBankIn implements BiFunction<Map<Integer, String>, List<BankInput.Transaction>, List<BankInOutput.AverageItem>> {

        @Override
        public List<BankInOutput.AverageItem> apply(Map<Integer, String> categoryNameById, List<BankInput.Transaction> transactions) {

            return categoryNameById.entrySet().stream()
                    .map(entry -> {
                        double averageSpendinOnCategory = transactions.stream()
                                .map(transaction -> {
                                    transaction.setDate(LocalDate.parse(transaction.getDate()).plusMonths(transaction.getMove_to_month()).toString());
                                    return transaction;
                                })
                                .filter(transaction -> entry.getKey().equals(transaction.getCategory_id()))
                                .filter(transaction -> transaction.getAccount_id() == 1)
                                .filter(transaction -> {
                                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                                    return transactionDate.isBefore(LocalDate.of(transactionDate.getYear(), month, transactionDate.getDayOfMonth()));
                                })
                                .filter(transaction -> {
                                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                                    LocalDate startDate = transactionDate.minusMonths(4);
                                    return transactionDate.isBefore(LocalDate.of(transactionDate.getYear(), month, transactionDate.getDayOfMonth())) && transactionDate.isAfter(startDate);
                                })
                                .mapToDouble(BankInput.Transaction::getAmount)
                                .average()
                                .orElse(0);
                        return new BankInOutput.AverageItem(entry.getKey(), entry.getValue(), averageSpendinOnCategory);
                    })
                    .collect(Collectors.toList());
        }
    }
}
