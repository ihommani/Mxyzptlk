import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.InputValidator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Level2 {

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
     * LEVEL 2
     * -------
     * It's now possible to move any transaction to the next or the previous month.
     * This is done via the `move_to_month` flag.
     * - When equal to 1, the transaction should be considered moved to the next month. (month + 1)
     * ex: 2012-01-12 => 2012-02-12
     * - When equal to -1, the transaction should be considered moved to the previous month. (month - 1)
     * ex: 2012-01-12 => 2011-12-12
     * - When equal to 0 the transaction keeps its date.
     * <p>
     * The user wants to know the total amount spent (or received) for every category for the current month (*June 2016*).
     * Take the new `move_to_month` flag into consideration.
     * <p>
     * OBJECTIVE
     * ---------
     * Write the code that generates output.json from input.json
     */
    public static void main(String[] args) {

        // basic verifications
        InputValidator.validate(args);

        Path inputPath = Paths.get(args[0]);

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();


    }
}
