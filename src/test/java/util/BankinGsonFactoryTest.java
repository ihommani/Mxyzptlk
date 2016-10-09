package util;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * Created on 09/10/16.<br/>
 */
public class BankinGsonFactoryTest {

    @Test
    public void should_Name_when() {
        BankinGsonFactory underTest = new BankinGsonFactory();

        // when
        Gson json = underTest.getJson();

        Assertions.assertThat(json.toJson(12.124354)).isEqualTo(12.12);
        Assertions.assertThat(json.toJson(12.00000)).isEqualTo(12);

    }
}