package PageObjects;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class EditEmployeeDetailsAPIs{
    Properties properties;
    public EditEmployeeDetailsAPIs(Properties properties){
        this.properties = properties;
    }
//    public Response getResponseFromEditEmployeePersonalDetails(JSONObject myJsonObject){
//        File jsonFile = new File(properties.getProperty("new_personal_details_path"));
//        return executePutAndGetResponse(properties.getProperty("edit_personal_details_path"), jsonFile);
//    }
//
//    public Response getResponseFromEditEmployeeBankDetails(JSONObject myJsonObject){
//        return executePutAndGetResponse(properties.getProperty("edit_bank_details_path"), myJsonObject);
//    }
//
//    public Response getResponseFromEditEmployeeEducationDetails(JSONObject myJsonObject){
//        return executePutAndGetResponse(properties.getProperty("edit_education_details_path"), myJsonObject);
//    }
//
//    public Response getResponseFromEditEmployeeEmploymentDetails(JSONObject myJsonObject){
//        return executePutAndGetResponse(properties.getProperty("edit_employment_history_path"), myJsonObject);
//    }
//    public Response getResponseFromEditEmployeeContactDetails(JSONObject myJsonObject){
//        return executePutAndGetResponse(properties.getProperty("edit_contact_details_path"), myJsonObject);
//    }
//
//    public Response getResponseFromGetEmployeePersonalDetails(){
//        return executeGetAndGetResponse(properties.getProperty("personal_details_path"));
//    }
//
//    public Response getResponseFromGetEmployeeBankDetails(){
//        return executeGetAndGetResponse(properties.getProperty("bank_detail_path"));
//    }
//
//    public Response getResponseFromGetEmployeeEducationDetails(){
//        return executeGetAndGetResponse(properties.getProperty("education_details_path"));
//    }
//
//    public Response getResponseFromGetEmployeeEmploymentDetails(){
//        return executeGetAndGetResponse(properties.getProperty("employment_history_path"));
//    }
//
//    public Response getResponseFromGetEmployeeContactDetails(){
//        return executeGetAndGetResponse(properties.getProperty("contact_details_path"));
//    }

    public Response executePutAndGetResponse(String path, File file){
        return given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/").
                header("Authorization",
                        "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuMTIzIiwiaWF0IjoxNjUxMTU1MTM5LCJleHAiOjE2NTEyNDE1Mzl9.FSEVt-_3qoK5YOR8HHoyaP-RKnRjR0TLIkqZ-UttqOfhbemNGb9wc06va_cP0KYDbvqYISqdO2pqoJd8HV-sBQ").
                header("Content-Type", properties.getProperty("content_type_json")).
                body(file).
                when().
                put(path).
                then().
                extract().
                response();
    }

    public Response executeGetAndGetResponse(String path){
        return given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/").
                header("Authorization",
                        "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuMTIzIiwiaWF0IjoxNjUxMTU1MTM5LCJleHAiOjE2NTEyNDE1Mzl9.FSEVt-_3qoK5YOR8HHoyaP-RKnRjR0TLIkqZ-UttqOfhbemNGb9wc06va_cP0KYDbvqYISqdO2pqoJd8HV-sBQ").
                header("Content-Type", properties.getProperty("content_type_json")).
                when().
                get(path).
                then().
                extract().
                response();
    }
}


