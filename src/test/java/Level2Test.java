import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 06/10/16.<br/>
 */
public class Level2Test {


    @Test
    public void should_Name_when() throws IOException {

        // given
        Path bankInput = Paths.get(Level2.class.getResource("level2/input.json").getPath());
        Path bankinOutput = Paths.get(Level2.class.getResource("level2/output.json").getPath());
        Path outputPath = Paths.get(Level2.class.getResource("level2").getPath()).resolve("testOutput.json");

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