package Test;
import PreRequisites.BaseClass;
import PageObjects.SignUp;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EmployeeSignupTest extends BaseClass {
    SignUp signup;

    @Test(priority = 1)
    // validate successful employee signup with valid details
    public void validateEmployeeSignup()
    {
        // creating object for signup class to get response
        signup = new SignUp();
        Response response = signup.validEmployeeSignup();

        // assert the status code of the response
        Assert.assertEquals(response.statusCode(),200);
        JSONObject object = new JSONObject(response.asString());

        // assert the signup success message from the response
        Assert.assertEquals(object.getString("message"),properties.getProperty("signup_success_message"));

        // assert content type
        Assert.assertEquals(response.header("Content-Type"),properties.getProperty("content_type_json"));
    }

    @Test(priority = 2)
    // validate unsuccessful employee signup with invalid details
    public void inValidateEmployeeSignup()
    {
        // creating object for signup class to get response
        signup = new SignUp();
        Response response = signup.inValidEmployeeSignup();

        // assert the status code of the response
        Assert.assertEquals(response.statusCode(),400);
        JSONObject object = new JSONObject(response.asString());

        // assert the signup error message from the response
        Assert.assertEquals(object.getString("message"),properties.getProperty("signup_failure_message"));

        // assert content type
        Assert.assertEquals(response.header("Content-Type"),properties.getProperty("content_type_json"));
    }
}
