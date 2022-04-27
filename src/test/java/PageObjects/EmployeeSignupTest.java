package PageObjects;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class EmployeeSignupTest {

    @Test
    public void validateEmployeeSignup()
    {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username","Kiruba");
        hashMap.put("password","123456");
        hashMap.put("email","test@Test.com");
        hashMap.put("company","Deloitte");

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        Response response =
                given().
                    baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/api").
                        header("Content-Type", "application/json").
                        body(json).
                when().
                    post("/auth/signup").
                then().
                    extract().response();

        Assert.assertEquals(response.statusCode(),200);

        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("message"),"User registered successfully!");
        Assert.assertEquals(response.header("Content-Type"),"application/json");
    }

    @Test
    public void inValidateEmployeeSignup()
    {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username","Kiruba");
        hashMap.put("password","123456");
        hashMap.put("email","test@Test.com");
        hashMap.put("company","Deloitte");

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        Response response =
                given().
                        baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/api").
                        header("Content-Type", "application/json").
                        body(json).
                when().
                        post("/auth/signup").
                then().
                        extract().response();

        Assert.assertEquals(response.statusCode(),400);

        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("message"),"Error: Username is already taken!");
        Assert.assertEquals(response.header("Content-Type"),"application/json");
    }
}
