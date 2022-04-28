package Test;

import PageObjects.EditEmployeeDetailsAPIs;
import PageObjects.Login;
import PreRequisites.BaseClass;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetEmployeeDetailsTest extends BaseClass {
    EditEmployeeDetailsAPIs editEmployeeDetailsAPIs;
    Login login;
    private String accessToken = "";
    @BeforeClass
    public void beforeClass(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs(properties);
        // Initializing the Login Class to get the access token of the User
        login = new Login();
        Response loginResponse = login.validEmployeeLogin();
        // Getting the access token of the User from the response
        JSONObject object = new JSONObject(loginResponse.asString());
        accessToken = object.getString("accessToken");
    }
    // Validating get Personal Details of the Employee
    @Test(priority = 1)
    public void ValidateGetEmployeePersonalDetails(){
        executeRequestAndValidateResponse("personal_details_path");
    }
    // Validating get Bank Details of the Employee
    @Test(priority = 2)
    public void ValidateGetEmployeeBankDetails(){
        executeRequestAndValidateResponse("bank_details_path");
    }
    // Validating get Education Details of the Employee
    @Test(priority = 3)
    public void ValidateGetEmployeeEducationDetails(){
        executeRequestAndValidateResponse("education_details_path");
    }
    // Validating get Employment Details of the Employee
    @Test(priority = 4)
    public void ValidateGetEmployeeEmploymentDetails(){
        executeRequestAndValidateResponse("employment_details_path");
    }
    // Validating get Contact Details of the Employee
    @Test(priority = 5)
    public void ValidateGetEmployeeContactDetails() {
        executeRequestAndValidateResponse("contact_details_path");
    }
    public void executeRequestAndValidateResponse(String urlPath){
        // Getting the url of API from properties file
        String path = properties.getProperty(urlPath);
        // Getting the Response from the EditEmployeeDetailsAPIs class
        Response response = editEmployeeDetailsAPIs.executeGetAndGetResponse(path, accessToken);
        // Validating the response
        Assert.assertEquals(200, response.getStatusCode());
        JSONObject responseJsonObj = new JSONObject(response.asString());
        JSONObject subJsonObj = (JSONObject) responseJsonObj.get("userId");
        Assert.assertEquals(subJsonObj.get("username"), properties.getProperty("employee_exist_username"));
    }
}