package Test;

import PageObjects.EditEmployeeDetailsAPIs;
import PageObjects.Login;
import PreRequisites.BaseClass;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class EditEmployeeDetailsTest extends BaseClass {
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
    // Validating edit Personal Details of the Employee
    @Test(priority = 1)
    public void ValidateEditEmployeePersonalDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_personal_details_path","new_personal_details_path");
    }
    // Validating edit Bank Details of the Employee
    @Test(priority = 2)
    public void ValidateEditEmployeeBankDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_bank_details_path","new_bank_details_path");
    }
    // Validating edit Education Details of the Employee
    @Test(priority = 3)
    public void ValidateEditEmployeeEducationDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_education_details_path","new_education_details_path");
    }
    // Validating edit Employment Details of the Employee
    @Test(priority = 4)
    public void ValidateEditEmployeeEmploymentDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_employment_details_path","new_employment_details_path");
    }
    // Validating edit Contact Details of the Employee
    @Test(priority = 5)
    public void ValidateEditEmployeeContactDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_contact_details_path","new_contact_details_path");
    }
    // Executing the Request, Getting the Response and Validating the Response
    public void executeRequestAndValidateResponse(String urlPath, String updateFilePath) throws FileNotFoundException {
        // Getting the url of API from properties file
        String path = properties.getProperty(urlPath);
        // Initialising the file with new details
        File jsonFile = new File(properties.getProperty(updateFilePath));
        String myJson = new Scanner(new File(properties.getProperty(updateFilePath))).useDelimiter("\\Z").next();
        // Taking the JSON from the file and converting it into JSONObject
        JSONObject myJsonObject = new JSONObject(myJson);
        String key = "";
        // Getting the key name that is going to be updated
        for (Iterator it = myJsonObject.keys(); it.hasNext(); ) {
            key = (String) it.next();
        }
        // Getting the Response from the EditEmployeeDetailsAPIs class
        Response response = editEmployeeDetailsAPIs.executePutAndGetResponse(path, jsonFile, accessToken);
        // Validating the response
        Assert.assertEquals(200, response.getStatusCode());
        JSONObject responseJsonObj = new JSONObject(response.asString());
        Assert.assertEquals(responseJsonObj.get(key), myJsonObject.get(key));
    }
}
