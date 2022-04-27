package Test;

import PageObjects.PostCall;
import PreRequisites.BaseClass;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;


public class LoginTest extends BaseClass {
    PostCall post;

    @Test
    public void validateEmployeeLogin() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("employee_exist_username"));
        hashMap.put("password", properties.getProperty("password"));

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/api/auth/signin", json);

        Assert.assertEquals(response.statusCode(), 200);
        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("username"), properties.getProperty("employee_exist_username"));
        Assert.assertEquals(object.getString("email"), properties.getProperty("employee_exist_email"));
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test
    public void inValidateEmployeeLogin() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("invalid_employee_username"));
        hashMap.put("password", properties.getProperty("password"));

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/api/auth/signin", json);

        Assert.assertEquals(response.statusCode(), 401);
        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("error"), "Unauthorized");
        Assert.assertEquals(object.getString("message"), "Bad credentials");
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test
    public void validateAdminLogin() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("admin_username"));
        hashMap.put("password", properties.getProperty("password"));

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/api/auth/signin", json);

        Assert.assertEquals(response.statusCode(), 200);
        JSONObject object = new JSONObject(response.asString());
        Assert.assertEquals(object.getString("username"), properties.getProperty("admin_username"));
        Assert.assertEquals(object.getString("email"), properties.getProperty("admin_mail"));
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test
    public void validateCompanyLogin() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("companyName", properties.getProperty("company_username"));
        hashMap.put("password", properties.getProperty("password"));
        System.out.println(hashMap);

        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        post = new PostCall();
        Response response = post.employeeSignup("/company/signin", json);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_text"));
    }
}
