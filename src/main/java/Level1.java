import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BankInOutput;
import model.BankInput;
import model.Category;
import model.Transaction;
import util.InputValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Level1 {

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
     * LEVEL 1
     * -------
     * Transactions are 'automagically' categorized by Bankin's API.
     * The user wants to know the total amount spent (or received) for very category for the current month (*June 2016*).
     * <p>
     * OBJECTIVE
     * ---------
     * Write the code that generates output.json from input.json
     */

    public static void main(String[] args) throws IOException {

        // basic verifications
        InputValidator.validate(args);

        Path inputPath = Paths.get(args[0]);

        // deserializer
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        Path outputPath = args.length == 2 ? Paths.get(args[1]) : Paths.get(Level1.class.getClassLoader().getResource("").getPath()).resolve("outputLevel1.json");
        try (BufferedReader bf = Files.newBufferedReader(inputPath);
             BufferedWriter bw = Files.newBufferedWriter(outputPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            BankInput bankInput = gson.fromJson(bf, BankInput.class);
            Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(Category::getId, Category::getName));
            List<BankInOutput.Item> outputItems = transformBankDatas(categoryNameById, bankInput.getTransactions());
            String output = gson.toJson(new BankInOutput(outputItems));
            bw.write(output);
            System.out.println("Output available to: " + outputPath);
        }
    }

    private static List<BankInOutput.Item> transformBankDatas(Map<Integer, String> categoryNameById, List<Transaction> transactions) {
        return categoryNameById.entrySet().stream()
                .map(entry -> {
                    double spendingOnCategory = transactions.stream()
                            .filter(transaction -> LocalDate.parse(transaction.getDate()).getMonth().getValue() == 6)
                            .filter(transaction -> entry.getKey().equals(transaction.getCategory_id()))
                            .mapToDouble(Transaction::getAmount)
                            .sum();
                    return new BankInOutput.Item(entry.getKey(), entry.getValue(), spendingOnCategory);
                })
                .collect(Collectors.toList());
    }
}
