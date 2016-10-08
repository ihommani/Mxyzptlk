package transformer;

import model.BankInOutput;
import model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankInputToBankIn implements BiFunction<Map<Integer, String>, List<Transaction>, List<BankInOutput.Item>> {

    @Override
    public List<BankInOutput.Item> apply(Map<Integer, String> categoryNameById, List<Transaction> transactions) {

        return categoryNameById.entrySet().stream()
                .map(entry -> {
                    double spendingOnCategory = transactions.stream()
                            .map(transaction -> {
                                transaction.setDate(LocalDate.parse(transaction.getDate()).plusMonths(transaction.getMove_to_month()).toString());
                                return transaction;
                            })
                            .filter(transaction -> LocalDate.parse(transaction.getDate()).getMonth().getValue() == 6)
                            .filter(transaction -> entry.getKey().equals(transaction.getCategory_id()))
                            .mapToDouble(Transaction::getAmount)
                            .sum();
                    return new BankInOutput.Item(entry.getKey(), entry.getValue(), spendingOnCategory);
                })
                .collect(Collectors.toList());
    }
}
