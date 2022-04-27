package PageObjects;

import PreRequisites.BaseClass;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class EditEmployeeDetailsAPIs extends BaseClass {

    public Response getResponseFromEditEmployeePersonalDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_personal_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeBankDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_bank_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeEducationDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_education_details_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeEmploymentDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_employment_history_path"), jsonFile);
    }

    public Response getResponseFromEditEmployeeContactDetails(){
        File jsonFile = new File(properties.getProperty("new_personal_detail_path"));
        return executePutAndGetResponse(properties.getProperty("edit_contact_details_path"),jsonFile);
    }

    private Response executePutAndGetResponse(String path, File file){
        requestSpecification.header("Authorization",
                "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWFuMTIzIiwiaWF0IjoxNjUxMDU4NDA5LCJleHAiOjE2NTExNDQ4MDl9.mEfIiGjDTkjzffBg33Yc5a7_7BSqdK-TtzuNJS6flJrynNPoMJ1hhrasIhR3iQkQM77rHWNTELzBSfL0GG81hg");
        requestSpecification.body(file);
        return given().
                spec(requestSpecification).
                when().
                put(path).
                then().
                spec(responseSpecification).
                extract().
                response();
    }
}


