package Listeners.PreRequisites;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {
    public static Properties properties;
    public final Logger log= LogManager.getLogger(BaseClass.class.getName());

    @BeforeTest
    public void init() throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/data.properties"));
    }
}
