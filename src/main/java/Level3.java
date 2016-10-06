public class Level3 {

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
     * LEVEL 3
     * -------
     * The user now wants to set budgets. A budget defines a maximum amount of monthly spending for a category.
     *
     * To help the user create his/her budgets, we need to display the average of his/her expenses for every category.
     * The average is calculated by taking the expenses of the last 3 months (not including the current month - June 2016).
     * We skip months that doesn't have any transactions.
     *
     * OBJECTIVE
     * ---------
     * Write the code that generates output.json from input.json
     */
    public static void main(String[] args) {
    }
}
