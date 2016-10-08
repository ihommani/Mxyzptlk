package util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created on 08/10/16.<br/>
 */
public class InputValidatorTest {

    @DataProvider(name = "badInputNumber")
    public static Object[][] badInputNumber() {
        return new Object[][]{
                {new String[]{}},
                {new String[]{"one", "two", "three"}}
        };
    }

    @Test(dataProvider = "badInputNumber", expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Usage: Level \\[path_input.json\\] \\[path_output_file.json\\]")
    public void should_fail_when_no_argument_is_given(String[] args) throws IOException {

        // given
        // when
        // then
        InputValidator.validate(args);

    }

    @DataProvider(name = "badPath")
    public static Object[][] badPath() {
        return new Object[][]{
                {new String[]{"one"}},
                {new String[]{"one", "two"}}
        };
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "Bad file path provided: .*")
    public void should_fail_when_providing_bad_path() throws IOException {

        // given
        // when
        // then
        InputValidator.validate(new String[]{InputValidator.class.getResource("").getPath()});

    }
}