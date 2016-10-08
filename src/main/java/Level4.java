import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BankInOutput;
import model.BankInput;
import transformer.BankinToItemAverage;
import util.InputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.Map;
import java.util.stream.Collectors;

public class Level4 {

    /**
     * INTRO
     * -----
     * We're building a piece of software that helps people better understand their finances. Let's call it Bankin'. ;)
     * Here is our plan:
     *  - let any user knows how much money they spend on each category
     *  - let any user sets budgets to help them save money
     *
     * This test is divided into 4 levels of increasing difficulty.
     *
     * You can have a look at the higher levels, but please do the simplest thing that could work for the level you're
     * currently solving.
     *
     * The levels become more complex over time, so you will probably have to re-use some code and adapt it to the new
     * requirements. A good way to solve this is to use OOP and add new layers of abstraction only when they become
     * necessary.
     * Don't hesitate to write shameless code at first and then refactor it in the next levels.
     *
     * HINTS
     * -----
     * CURRENT MONTH IS JUNE 2016.
     * All prices are in EUR.
     * Use Java 8 APIs (see: java.nio.file) to quickly read the file.
     * Use Gson (https://github.com/google/gson) to parse JSON file.
     * Don't use any other libraries.
     * `output.json` can be printed in the console or in a file.
     *
     * LEVEL 4
     * -------
     * The user has now set up his/her budgets.
     * The user wants to know the balance of his/her account at the end of the month according to the budgets s/he has setup.
     *
     * The estimated balance of an account at the end of the month is given by the following formula:
     * est. account balance = current account balance -  ∑ of what remains to be spent on each category that has a budget that is not exceeded.
     * Note: A budget is exceeded when the total expenses of the budget is greater than its limit. (e.g I spent 150€
     * in shopping this month and my shopping budget is 100€)
     *
     * Also,
     * remains to be spent of category X = budget's limit of category X - ∑ transaction's amount of category X for the current month.
     * e.g I bought a pair of shoes for 50€ and a hat for 40€ and my shopping budget is 150€. The remains to be spent for my shopping
     * budget is 60€.
     * Note: if remains to be spent is < 0, budget is exceeded
     *
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

        Path outputPath = args.length == 2 ? Paths.get(args[1]) : Paths.get(Level1.class.getClassLoader().getResource("").getPath()).resolve("outputLevel3.json");
        try (BufferedReader bf = Files.newBufferedReader(inputPath)) {
            BankInput bankInput = gson.fromJson(bf, BankInput.class);
            Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(BankInput.Category::getId, BankInput.Category::getName));
            BankInOutput src = new BankInOutput(new BankinToItemAverage(Month.JUNE).transform(categoryNameById, bankInput.getTransactions()));
            System.out.println("Amount of account (id=1): " + outputPath);
        }

    }
}
