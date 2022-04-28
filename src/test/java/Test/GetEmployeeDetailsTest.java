package Test;

import PageObjects.EditEmployeeDetailsAPIs;
import Listeners.PreRequisites.BaseClass;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetEmployeeDetailsTest extends BaseClass {
    EditEmployeeDetailsAPIs editEmployeeDetailsAPIs;
    @BeforeClass
    public void beforeClass(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs(properties);
    }
    @Test(priority = 1)
    public void ValidateGetEmployeePersonalDetails(){
        executeRequestAndValidateResponse("personal_details_path");
    }
    @Test(priority = 2)
    public void ValidateGetEmployeeBankDetails(){
        executeRequestAndValidateResponse("bank_details_path");
    }
    @Test(priority = 3)
    public void ValidateGetEmployeeEducationDetails(){
        executeRequestAndValidateResponse("education_details_path");
    }
    @Test(priority = 4)
    public void ValidateGetEmployeeEmploymentDetails(){
        executeRequestAndValidateResponse("employment_details_path");
    }
    @Test(priority = 5)
    public void ValidateGetEmployeeContactDetails() {
        executeRequestAndValidateResponse("contact_details_path");
    }
    public void executeRequestAndValidateResponse(String urlPath){
        String path = properties.getProperty(urlPath);
        Response response = editEmployeeDetailsAPIs.executeGetAndGetResponse(path);
        JSONObject responseJsonObj = new JSONObject(response.asString());
        System.out.println(responseJsonObj);
        Assert.assertEquals(200, response.getStatusCode());
    }
}