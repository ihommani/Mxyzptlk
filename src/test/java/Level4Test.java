import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 06/10/16.<br/>
 */
public class Level4Test {

    @Test
    public void should_return_the_estimated_balance_of_account() throws IOException {

        // given
        Path bankInput = Paths.get(Level4.class.getResource("level4/input.json").getPath());

        // when
        Level4.main(new String[]{bankInput.toString()});

        // then

    }
}