package PageObjects;

import io.restassured.response.Response;

import java.io.File;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class EditEmployeeDetailsAPIs{
    Properties properties;
    // Constructor to get the properties file from the Test class
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

    // Function to execute put request: returns response
    public Response executePutAndGetResponse(String path, File file, String accessToken){
        return given().
                baseUri(properties.getProperty("base_URL")).
                header("Authorization",
                        "Bearer " + accessToken).
                header("Content-Type", properties.getProperty("content_type_json")).
                body(file).
                when().
                put(path).
                then().
                extract().
                response();
    }
    // Function to execute get request: returns response
    public Response executeGetAndGetResponse(String path, String accessToken){
        return given().
                baseUri(properties.getProperty("base_URL")).
                header("Authorization",
                        "Bearer " + accessToken).
                header("Content-Type", properties.getProperty("content_type_json")).
                when().
                get(path).
                then().
                extract().
                response();
    }
}


