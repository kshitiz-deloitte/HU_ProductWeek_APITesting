package PageObjects;

import PreRequisites.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class EditEmployeeDetailsAPIs{
    Properties properties;
    public EditEmployeeDetailsAPIs(Properties properties){
        this.properties = properties;
    }
    public Response getResponseFromEditEmployeePersonalDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_personal_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeBankDetails(){
        File jsonFile = new File(properties.getProperty("new_bank_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_bank_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeEducationDetails(){
        File jsonFile = new File(properties.getProperty("new_education_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_education_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeEmploymentDetails(){
        File jsonFile = new File(properties.getProperty("new_employment_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_employment_history_path"), jsonFile);
    }
    public Response getResponseFromEditEmployeeContactDetails(){
        File jsonFile = new File(properties.getProperty("new_contact_detail_path"));
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    public Response getResponseFromGetEmployeePersonalDetails(){
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    public Response getResponseFromGetEmployeeBankDetails(){
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    public Response getResponseFromGetEmployeeEducationDetails(){
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    public Response getResponseFromGetEmployeeEmploymentDetails(){
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    public Response getResponseFromGetEmployeeContactDetails(){
        return executeGetAndGetResponse(properties.getProperty("edit_contact_details_path"));
    }

    private Response executePutAndGetResponse(String path, File file){
        return given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/").
                header("Authorization",
                        "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuMTIzIiwiaWF0IjoxNjUxMDU4NDA5LCJleHAiOjE2NTExNDQ4MDl9.mEfIiGjDTkjzffBg33Yc5a7_7BSqdK-TtzuNJS6flJrynNPoMJ1hhrasIhR3iQkQM77rHWNTELzBSfL0GG81hg").
                header("Content-Type", properties.getProperty("content_type_json")).
                body(file).
                when().
                put(path).
                then().
                extract().
                response();
    }

    private Response executeGetAndGetResponse(String path){
        return given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app/").
                header("Authorization",
                        "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuMTIzIiwiaWF0IjoxNjUxMDU4NDA5LCJleHAiOjE2NTExNDQ4MDl9.mEfIiGjDTkjzffBg33Yc5a7_7BSqdK-TtzuNJS6flJrynNPoMJ1hhrasIhR3iQkQM77rHWNTELzBSfL0GG81hg").
                header("Content-Type", properties.getProperty("content_type_json")).
                when().
                get(path).
                then().
                extract().
                response();
    }
}


