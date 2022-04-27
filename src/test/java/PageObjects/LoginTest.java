package PageObjects;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LoginTest {
    @Test
    public void validateEmployeeLogin()
    {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username","Kiruba");
        hashMap.put("password","123456");

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        Response response =
                given().
                        baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/api").
                        header("Content-Type", "application/json").
                        body(json).
                when().
                        post("/auth/signin").
                then().
                        extract().response();

        Assert.assertEquals(response.statusCode(),200);

        JSONObject object = new JSONObject(response.asString());



        Assert.assertEquals(response.header("Content-Type"),"application/json");
    }
}
