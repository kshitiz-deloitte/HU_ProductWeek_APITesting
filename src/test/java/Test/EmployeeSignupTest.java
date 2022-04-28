package Test;
import PageObjects.PostCall;
import Listeners.PreRequisites.BaseClass;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EmployeeSignupTest extends BaseClass {
    PostCall post;

    @Test
    public void validateEmployeeSignup()
    {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username",properties.getProperty("employee_new_username"));
        hashMap.put("password",properties.getProperty("password"));
        hashMap.put("email",properties.getProperty("employee_new_email"));
        hashMap.put("company",properties.getProperty("company"));

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/api/auth/signup", json);

        Assert.assertEquals(response.statusCode(),200);

        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("message"),properties.getProperty("signup_success_message"));
        Assert.assertEquals(response.header("Content-Type"),properties.getProperty("content_type_json"));
    }

    @Test
    public void inValidateEmployeeSignup()
    {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username",properties.getProperty("employee_exist_username"));
        hashMap.put("password",properties.getProperty("password"));
        hashMap.put("email",properties.getProperty("employee_exist_email"));
        hashMap.put("company",properties.getProperty("company"));

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/api/auth/signup", json);

        Assert.assertEquals(response.statusCode(),400);

        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("message"),properties.getProperty("signup_failure_message"));
        Assert.assertEquals(response.header("Content-Type"),properties.getProperty("content_type_json"));
    }
}
