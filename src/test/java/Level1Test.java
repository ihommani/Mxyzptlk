import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 06/10/16.<br/>
 */
public class Level1Test {

    @DataProvider(name = "badInput")
    public static Object[][] badInput() {
        return new Object[][]{
                {new String[]{}},
                {new String[]{"one", "two", "three"}}
        };
    }

    @Test(dataProvider = "badInput", expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Usage: Level1 \\[path_input.json\\] \\[path_output_file.json\\]")
    public void should_fail_when_no_argument_is_given(String[] args) throws IOException {

        // given
        // when
        // then
        Level1.main(args);

    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Bad file path provided:.*")
    public void should_fail_when_providing_bad_path() throws IOException {

        // given
        // when
        // then
        Level1.main(new String[]{Level1.class.getResource("").getPath()});

    }

    @Test
    public void should_generate_output() throws IOException {

        // given
        Path bankInput = Paths.get(Level1.class.getResource("level1/input.json").getPath());
        Path bankinOutput = Paths.get(Level1.class.getResource("level1/output.json").getPath());
        Path outputPath = Paths.get(Level1.class.getResource("level1").getPath()).resolve("testOutput.json");

        // when
        Level1.main(new String[]{bankInput.toString(), String.valueOf(outputPath)});

        // then
        try(BufferedReader bfTest = Files.newBufferedReader(outputPath);
        BufferedReader bfCompare = Files.newBufferedReader(bankinOutput) ){
            String testOutput = bfTest.lines().reduce((s, s2) -> s + '\n' + s2).get();
            String bankin = bfCompare.lines().reduce((s, s2) -> s + '\n' + s2).get();

            Assertions.assertThat(testOutput).isNotEmpty();
        }

    }
}
