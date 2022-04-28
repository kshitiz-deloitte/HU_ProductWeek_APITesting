package Test;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class EmployeeDashboardAPITest {
    public static Properties properties;
    File file;

    //Properties
    @BeforeTest
    public void getting_properties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/data.properties"));
    }

    //Uploading AadharCard
    @Test(priority = 1)
    public void validatingUploadAadhar() {
        file = new File(System.getProperty("user.dir") + properties.getProperty("aadhar"));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                multiPart("file", file).
                when().
                put("/1/upload").
                then().
                assertThat().
                statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    //Uploading Pan
    @Test(priority = 2)
    public void validatingUploadPan() {
        file = new File(System.getProperty("user.dir") + properties.getProperty("pan"));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                multiPart("file", file).
                when().
                put("/1/upload-pan").
                then().
                assertThat().
                statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    //Uploading Marksheet
    @Test(priority = 3)
    public void validatingUploadMarksheet() {
        file = new File(System.getProperty("user.dir") + properties.getProperty("marksheet"));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                multiPart("file", file).
                when().
                put("/1/upload-marksheet").
                then().
                assertThat().
                statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    //Invalidating the Upload of Aadhar
    @Test(priority = 4)
    public void invalidateUploadAadhar() {
        file = new File(System.getProperty("user.dir") + properties.getProperty(""));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                when().
                put("/1/upload").
                then().
                assertThat().
                statusCode(500).extract().response();
        System.out.println(response.asString());

    }

    //Invalidating the Upload of Pan
    @Test(priority = 5)
    public void invalidateUploadPan() {
        file = new File(System.getProperty("user.dir") + properties.getProperty(""));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                when().
                put("/1/upload-pan").
                then().
                assertThat().
                statusCode(500).extract().response();
        System.out.println(response.asString());

    }

    //Invalidating the Upload of Marksheet
    @Test(priority = 6)
    public void invalidateUploadMarksheet() {
        file = new File(System.getProperty("user.dir") + properties.getProperty(""));
        Response response = given().
                baseUri(properties.getProperty("base_URL")).
                when().
                put("/1/upload-marksheet").
                then().
                assertThat().
                statusCode(500).extract().response();
        System.out.println(response.asString());

    }
}

