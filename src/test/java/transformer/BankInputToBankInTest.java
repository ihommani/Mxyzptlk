package transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BankInOutput;
import model.BankInput;
import model.Category;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankInputToBankInTest {

    private BankInputToBankIn underTest = new BankInputToBankIn();

    private Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();


    @Test
    public void should_transform_bankinput_into_bakin_data_model() throws IOException {

        // given
        BufferedReader bf = Files.newBufferedReader(Paths.get(BankInputToBankIn.class.getClassLoader().getResource("transformer/input.json").getPath()));
        BankInput bankInput = gson.fromJson(bf, BankInput.class);

        Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(Category::getId, Category::getName));

        // when
        List<BankInOutput.Item> items = underTest.apply(categoryNameById, bankInput.getTransactions());

        // then
        Assertions.assertThat(items).hasSize(10);
        Assertions.assertThat(items.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(items.get(0).getName()).isEqualTo("Incomes");
        Assertions.assertThat(items.get(0).getTotal()).isEqualTo(29.0);

    }
}