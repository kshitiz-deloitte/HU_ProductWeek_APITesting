package PageObjects;

import Listeners.PreRequisites.BaseClass;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostCall extends BaseClass {

    public Response employeeSignup(String path, String file){

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
