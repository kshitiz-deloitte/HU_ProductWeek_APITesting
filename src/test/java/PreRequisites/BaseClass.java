package PreRequisites;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {
    public static Properties properties;
    public static RequestSpecBuilder requestSpecBuilder;
    public static RequestSpecification requestSpecification;
    public static ResponseSpecBuilder responseSpecBuilder;
    public static ResponseSpecification responseSpecification;

    @BeforeTest
    public void init() throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/data.properties"));
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/");
        requestSpecification = RestAssured.with().
                spec(requestSpecBuilder.build());
        requestSpecification.header("Content-Type", properties.getProperty("content_type_json"));
        responseSpecBuilder = new ResponseSpecBuilder();
//                expectContentType(ContentType.JSON);
        responseSpecification = responseSpecBuilder.build();
    }
}
