package PageObjects;
import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.HashMap;
import static PreRequisites.BaseClass.properties;
import static io.restassured.RestAssured.given;

public class SignUp {

    Gson gson;
    String json;

    // method to return response of valid employee signup
    public Response validEmployeeSignup()
    {
        // hashmap for input details
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username",properties.getProperty("employee_new_username"));
        hashMap.put("password",properties.getProperty("password"));
        hashMap.put("email",properties.getProperty("employee_new_email"));
        hashMap.put("company",properties.getProperty("company"));

        // convert hashmap to string using gson
        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        return postCall("/api/auth/signup", json);

    }

    // method to return response of invalid employee signup
    public Response inValidEmployeeSignup()
    {
        // hashmap for input details
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("username",properties.getProperty("employee_exist_username"));
        hashMap.put("password",properties.getProperty("password"));
        hashMap.put("email",properties.getProperty("employee_exist_email"));
        hashMap.put("company",properties.getProperty("company"));

        // convert hashmap to string using gson
        Gson gson = new Gson();
        String json = gson.toJson(hashMap);

        return postCall("/api/auth/signup", json);
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
