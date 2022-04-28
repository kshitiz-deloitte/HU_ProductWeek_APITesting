package PageObjects;

import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.HashMap;
import static PreRequisites.BaseClass.properties;
import static io.restassured.RestAssured.given;

public class Login{

    Gson gson;
    String json;

    // method to return response for valid employee login
    public Response validEmployeeLogin()
    {
        // hashmap for input details
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("employee_exist_username"));
        hashMap.put("password", properties.getProperty("password"));

        // convert hashmap to string using gson
        gson = new Gson();
        json = gson.toJson(hashMap);

        return postCall("/api/auth/signin", json);
    }

    // method to return response for invalid employee login
    public Response inValidEmployeeLogin()
    {
        // hashmap for input details
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("invalid_employee_username"));
        hashMap.put("password", properties.getProperty("password"));

        // convert hashmap to string using gson
        gson = new Gson();
        json = gson.toJson(hashMap);

        return postCall("/api/auth/signin", json);
    }

    // method to return response for valid admin login
    public Response validAdminLogin()
    {
        // hashmap for input details
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", properties.getProperty("admin_username"));
        hashMap.put("password", properties.getProperty("password"));

        // convert hashmap to string using gson
        gson = new Gson();
        json = gson.toJson(hashMap);

        return postCall("/api/auth/signin", json);
    }

    // method to return response for valid company login
    public Response validCompanyLogin()
    {
        // hashmap for input details
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("companyName", properties.getProperty("company_username"));
        hashMap.put("password", properties.getProperty("password"));

        // convert hashmap to string using gson
        gson = new Gson();
        json = gson.toJson(hashMap);

        return postCall("/company/signin", json);
    }

    // method to return response for invalid company login
    public Response inValidCompanyLogin()
    {
        // hashmap for input details
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("companyName", properties.getProperty("invalid_company_name"));
        hashMap.put("password", properties.getProperty("password"));

        // convert hashmap to string using gson
        gson = new Gson();
        json = gson.toJson(hashMap);

        return postCall("/company/signin", json);
    }

    // method which return response with respect to path and file
    public Response postCall(String path, String file){

             return given().
                    baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app").
                    header("Content-Type", properties.getProperty("content_type_json")).
                    body(file).
                when().
                    post(path).
                then().
                    extract().response();
    }
}
