package Test;

import PageObjects.EditEmployeeDetailsAPIs;
import PreRequisites.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class EditEmployeeDetailsTest extends BaseClass {
    EditEmployeeDetailsAPIs editEmployeeDetailsAPIs;
    @Test(priority = 1)
    public void editEmployeePersonalDetails(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs();
        Response response = editEmployeeDetailsAPIs.getResponseFromEditEmployeePersonalDetails();
        System.out.println(response.asString());
    }
    @Test(priority = 2)
    public void editEmployeeBankDetails(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs();
        Response response = editEmployeeDetailsAPIs.getResponseFromEditEmployeeBankDetails();
        System.out.println(response.asString());
    }
    @Test(priority = 3)
    public void editEmployeeEducationDetails(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs();
        Response response = editEmployeeDetailsAPIs.getResponseFromEditEmployeeEducationDetails();
        System.out.println(response.asString());
    }
    @Test(priority = 4)
    public void editEmployeeEmploymentDetails(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs();
        Response response = editEmployeeDetailsAPIs.getResponseFromEditEmployeeEmploymentDetails();
        System.out.println(response.asString());
    }

    @Test(priority = 5)
    public void editEmployeeContactDetails(){
        editEmployeeDetailsAPIs = new EditEmployeeDetailsAPIs();
        Response response = editEmployeeDetailsAPIs.getResponseFromEditEmployeeContactDetails();
        System.out.println(response.asString());
    }

}
