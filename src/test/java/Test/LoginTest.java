package Test;

import PageObjects.Login;
import PreRequisites.BaseClass;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseClass {
    Login login;


    @Test(priority = 1)
    // validate successful employee login with valid details
    public void validateEmployeeLogin() {

        // creating object for login class to get response
        login = new Login();
        Response response = login.validEmployeeLogin();

        // assert status code
        Assert.assertEquals(response.statusCode(), 200);
        JSONObject object = new JSONObject(response.asString());

        // assert username of employee from the response
        Assert.assertEquals(object.getString("username"), properties.getProperty("employee_exist_username"));

        // assert email of employee from the response
        Assert.assertEquals(object.getString("email"), properties.getProperty("employee_exist_email"));

        // assert content type
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test(priority = 2)
    // validate unsuccessful employee login with invalid details
    public void inValidateEmployeeLogin() {

        // creating object for login class to get response
        login = new Login();
        Response response = login.inValidEmployeeLogin();

        // assert status code
        Assert.assertEquals(response.statusCode(), 401);
        JSONObject object = new JSONObject(response.asString());

        // assert error message from the response
        Assert.assertEquals(object.getString("error"), "Unauthorized");
        Assert.assertEquals(object.getString("message"), "Bad credentials");

        // assert content type
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test(priority = 3)
    // validate successful admin login with valid details
    public void validateAdminLogin() {

        // creating object for login class to get response
        login = new Login();
        Response response = login.validAdminLogin();

        // assert status code
        Assert.assertEquals(response.statusCode(), 200);
        JSONObject object = new JSONObject(response.asString());

        // assert username of admin from the response
        Assert.assertEquals(object.getString("username"), properties.getProperty("admin_username"));
        // assert email of admin from the response
        Assert.assertEquals(object.getString("email"), properties.getProperty("admin_mail"));

        // assert content type
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }

    @Test(priority = 4)
    // validate successful company login with valid details
    public void validateCompanyLogin() {

        // creating object for login class to get response
        login = new Login();
        Response response = login.validCompanyLogin();

        // assert status code
        Assert.assertEquals(response.statusCode(), 200);

        // assert username of company from the response
        Assert.assertEquals(properties.getProperty("company"),response.asString());

        // assert content type
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_text"));
    }

    @Test(priority = 5)
    // validate unsuccessful company login with invalid details
    public void inValidateCompanyLogin()
    {

        // creating object for login class to get response
        login = new Login();
        Response response = login.inValidCompanyLogin();

        // assert status code
        Assert.assertEquals(response.statusCode(), 400);
        JSONObject object = new JSONObject(response.asString());

        // assert sign in error message of company from the response
        Assert.assertEquals(properties.getProperty("company_signin_error_message"),object.getString("message"));

        // assert content type
        Assert.assertEquals(response.header("Content-Type"), properties.getProperty("content_type_json"));
    }
}
