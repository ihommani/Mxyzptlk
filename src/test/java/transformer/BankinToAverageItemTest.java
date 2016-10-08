package transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BankInput;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankinToAverageItemTest {

    private Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    @Test
    public void should_transform_input_into_items_contening_average_values() throws IOException {

        // given
        BankinToItemAverage underTest = new BankinToItemAverage(Month.JUNE);
        BufferedReader bf = Files.newBufferedReader(Paths.get(BankinToItemSum.class.getClassLoader().getResource("transformer/inputLevel3.json").getPath()));
        BankInput bankInput = gson.fromJson(bf, BankInput.class);

        // when
        Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(BankInput.Category::getId, BankInput.Category::getName));
        underTest.transform(categoryNameById, bankInput.getTransactions());

        // then

    }
}