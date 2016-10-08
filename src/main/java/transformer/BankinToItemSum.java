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
public class BankinToItemSum {

    private final Month month;

    private final BankinToItemSum.BankInputToBankIn bankInputToBankIn = new BankinToItemSum.BankInputToBankIn();

    public BankinToItemSum(Month month) {
        this.month = month;
    }

    public List<BankInOutput.SumItem> transform(Map<Integer, String> categoryNameById, List<BankInput.Transaction> transactions) {
        return bankInputToBankIn.apply(categoryNameById, transactions);
    }

    class BankInputToBankIn implements BiFunction<Map<Integer, String>, List<BankInput.Transaction>, List<BankInOutput.SumItem>> {

        @Override
        public List<BankInOutput.SumItem> apply(Map<Integer, String> categoryNameById, List<BankInput.Transaction> transactions) {
            return categoryNameById.entrySet().stream()
                    .map(entry -> {
                        double spendingOnCategory = transactions.stream()
                                .map(transaction -> {
                                    transaction.setDate(LocalDate.parse(transaction.getDate()).plusMonths(transaction.getMove_to_month()).toString());
                                    return transaction;
                                })
                                .filter(transaction -> LocalDate.parse(transaction.getDate()).getMonth().getValue() == 6)
                                .filter(transaction -> entry.getKey().equals(transaction.getCategory_id()))
                                .mapToDouble(BankInput.Transaction::getAmount)
                                .sum();
                        return new BankInOutput.SumItem(entry.getKey(), entry.getValue(), spendingOnCategory);
                    })
                    .collect(Collectors.toList());
        }
    }
}
