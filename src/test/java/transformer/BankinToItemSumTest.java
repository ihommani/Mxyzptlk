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
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 08/10/16.<br/>
 */
public class BankinToItemSumTest {

    private BankinToItemSum underTest = new BankinToItemSum(Month.JUNE);

    private Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();


    @Test
    public void should_transform_bankinput_into_bakin_data_model() throws IOException {

        // given
        BufferedReader bf = Files.newBufferedReader(Paths.get(BankinToItemSum.class.getClassLoader().getResource("transformer/input.json").getPath()));
        BankInput bankInput = gson.fromJson(bf, BankInput.class);

        Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(Category::getId, Category::getName));

        // when
        List<BankInOutput.SumItem> sumItems = underTest.transform(categoryNameById, bankInput.getTransactions());

        // then
        Assertions.assertThat(sumItems).hasSize(10);
        Assertions.assertThat(sumItems.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(sumItems.get(0).getName()).isEqualTo("Incomes");
        Assertions.assertThat(sumItems.get(0).getTotal()).isEqualTo(29.0);

    }

    @Test
    public void should_transform_bankinput2_into_bakin_data_model() throws IOException {

        // given
        BufferedReader bf = Files.newBufferedReader(Paths.get(BankinToItemSum.class.getClassLoader().getResource("transformer/inputlevel2.json").getPath()));
        BankInput bankInput = gson.fromJson(bf, BankInput.class);

        Map<Integer, String> categoryNameById = bankInput.getCategories().stream().collect(Collectors.toMap(Category::getId, Category::getName));

        // when
        List<BankInOutput.SumItem> sumItems = underTest.transform(categoryNameById, bankInput.getTransactions());

        // then
        Assertions.assertThat(sumItems).hasSize(10);
        Assertions.assertThat(sumItems.get(1).getId()).isEqualTo(2);
        Assertions.assertThat(sumItems.get(1).getName()).isEqualTo("Home");
        Assertions.assertThat(sumItems.get(1).getTotal()).isEqualTo(-1706.16);

    }

}