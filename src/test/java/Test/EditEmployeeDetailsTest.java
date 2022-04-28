package Test;

import PageObjects.EditEmployeeDetailsAPIs;
import Listeners.PreRequisites.BaseClass;

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
    @BeforeClass
    public void beforeClass(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs(properties);
    }
    @Test(priority = 1)
    public void ValidateEditEmployeePersonalDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_personal_details_path","new_personal_details_path");
    }
    @Test(priority = 2)
    public void ValidateEditEmployeeBankDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_bank_details_path","new_bank_details_path");
    }
    @Test(priority = 3)
    public void ValidateEditEmployeeEducationDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_education_details_path","new_education_details_path");
    }
    @Test(priority = 4)
    public void ValidateEditEmployeeEmploymentDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_employment_details_path","new_employment_details_path");
    }

    @Test(priority = 5)
    public void ValidateEditEmployeeContactDetails() throws FileNotFoundException {
        executeRequestAndValidateResponse("edit_contact_details_path","new_contact_details_path");
    }

    public void executeRequestAndValidateResponse(String urlPath, String updateFilePath) throws FileNotFoundException {
        String path = properties.getProperty(urlPath);
        File jsonFile = new File(properties.getProperty(updateFilePath));
        String myJson = new Scanner(new File(properties.getProperty(updateFilePath))).useDelimiter("\\Z").next();
        JSONObject myJsonObject = new JSONObject(myJson);
        String key = "";
        for (Iterator it = myJsonObject.keys(); it.hasNext(); ) {
            key = (String) it.next();
        }
        Response response = editEmployeeDetailsAPIs.executePutAndGetResponse(path, jsonFile);
        JSONObject responseJsonObj = new JSONObject(response.asString());
        Assert.assertEquals(responseJsonObj.get(key), myJsonObject.get(key));
        Assert.assertEquals(200, response.getStatusCode());
    }

}
