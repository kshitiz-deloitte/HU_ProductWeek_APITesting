package PreRequisites;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {
    public static Properties properties;

    @BeforeTest
    public void init() throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/data.properties"));
    }
}
