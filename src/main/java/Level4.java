import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BankInOutput;
import model.BankInput;
import transformer.BankinToItemSum;
import util.InputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Level4 {

    /**
     * INTRO
     * -----
     * We're building a piece of software that helps people better understand their finances. Let's call it Bankin'. ;)
     * Here is our plan:
     * - let any user knows how much money they spend on each category
     * - let any user sets budgets to help them save money
     * <p>
     * This test is divided into 4 levels of increasing difficulty.
     * <p>
     * You can have a look at the higher levels, but please do the simplest thing that could work for the level you're
     * currently solving.
     * <p>
     * The levels become more complex over time, so you will probably have to re-use some code and adapt it to the new
     * requirements. A good way to solve this is to use OOP and add new layers of abstraction only when they become
     * necessary.
     * Don't hesitate to write shameless code at first and then refactor it in the next levels.
     * <p>
     * HINTS
     * -----
     * CURRENT MONTH IS JUNE 2016.
     * All prices are in EUR.
     * Use Java 8 APIs (see: java.nio.file) to quickly read the file.
     * Use Gson (https://github.com/google/gson) to parse JSON file.
     * Don't use any other libraries.
     * `output.json` can be printed in the console or in a file.
     * <p>
     * LEVEL 4
     * -------
     * The user has now set up his/her budgets.
     * The user wants to know the balance of his/her account at the end of the month according to the budgets s/he has setup.
     * <p>
     * The estimated balance of an account at the end of the month is given by the following formula:
     * est. account balance = current account balance -  ∑ of what remains to be spent on each category that has a budget that is not exceeded.
     * Note: A budget is exceeded when the total expenses of the budget is greater than its limit. (e.g I spent 150€
     * in shopping this month and my shopping budget is 100€)
     * <p>
     * Also,
     * remains to be spent of category X = budget's limit of category X - ∑ transaction's amount of category X for the current month.
     * e.g I bought a pair of shoes for 50€ and a hat for 40€ and my shopping budget is 150€. The remains to be spent for my shopping
     * budget is 60€.
     * Note: if remains to be spent is < 0, budget is exceeded
     * <p>
     * OBJECTIVE
     * ---------
     * Write the code that prints the estimated amount of account (id = 1) from input.json //TODO :the estimated balance of the account
     */
    public static void main(String[] args) throws IOException {

        // basic verifications
        InputValidator.validate(args);

        Path inputPath = Paths.get(args[0]);

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        try (BufferedReader bf = Files.newBufferedReader(inputPath)) {
            BankInput bankInput = gson.fromJson(bf, BankInput.class);
            BankInput.Account account1 = bankInput.getAccounts().stream().filter(account -> account.getId() == 1).findFirst().get();

            Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(BankInput.Category::getId, BankInput.Category::getName));
            Map<Integer, BankInput.Budget> budgetByCategoryId = bankInput.getBudgets().stream().collect(Collectors.toMap(BankInput.Budget::getCategory_id, budget -> budget));

            List<BankInOutput.SumItem> sumItems = new BankinToItemSum(Month.JUNE).transform(categoryNameById, bankInput.getTransactions());

            double totalOfRemainToBeSpent = sumItems.stream()
                    .filter(sumItem -> budgetByCategoryId.containsKey(sumItem.getId()))
                    .filter(sumItem -> budgetByCategoryId.get(sumItem.getId()).getLimit() + sumItem.getTotal() > 0) // TODO: see how to substract double. Here -- give +
                    .peek(sumItem -> System.out.println("remain " + (budgetByCategoryId.get(sumItem.getId()).getLimit() + sumItem.getTotal()) + " to spend on category" + sumItem.getId()))
                    .mapToDouble(sumItem -> budgetByCategoryId.get(sumItem.getId()).getLimit() - sumItem.getTotal())
                    .sum();

            double accountBalance = account1.getBalance() - totalOfRemainToBeSpent;
            System.out.println("Balance of account (id=1): " + accountBalance);
        }
    }
}
