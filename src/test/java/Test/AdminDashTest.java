package Test;

import PreRequisites.BaseClass;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AdminDashTest extends BaseClass {
    // TC011-Downloading the Marksheet
    @Test(priority = 1)
    public void downloadMarkSheet() {
        String response = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/octet-stream").
        when().
                get("/kyc/get/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
    }

    // TC010-Downloading the Pan card
    @Test(priority = 2)
    public void downloadPanCard() {
        String response = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/octet-stream").
        when().
                get("/kyc/get/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
    }

    // TC009-Downloading the Aadhar card
    @Test(priority = 3)
    public void downloadAadharCard() {
        String response = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/octet-stream").
        when().
                get("/kyc/get/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
    }

    // TC008-Validating the Company name
    @Test(priority = 4)
    public void validatePerticularCompanyName() {
        String response = given().baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/json").
        when().
                get("/api/test/"+properties.getProperty("comp_Name")+"/user").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String value = js.getString( "company" );
        for(int size=0;size<value.length();size++){
            if(value.equals(properties.getProperty("comp_Name"))==true)throw new AssertionError("User doesn't belongs to Company");
        }
    }

    // TC007-Json Schema validator for Comapny name
    @Test(priority = 5)
    public void jsonValidatorForParticularCompanyName(){
        given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app").
        when().
                get("/api/test/Deloitte/user").
        then().
                body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/json/particularCompanyName.json")));
    }

    //TC006-Validating the Mail response
    @Test(priority = 6)
    public void mailResponse(){
        File jsonFile = new File(properties.getProperty("send_mail_path"));
        String response = given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app").
                header("content-type", "application/json").
                body(jsonFile).
        when().
                post("/api/user/sendemail").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
    }

    // TC05-Validator for Company register
    @Test(priority = 7)
    public void jsonValidatorForCompanyRegister(){
        given().
                baseUri(properties.getProperty("base_URL")).
        when().
                get("/company/all").
        then().
                body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/json/comp_register.json")));
    }

    // TC004-Editing the Kyc status by Id
    @Test(priority = 8)
    public void editKycStatuswithID(){
        File jsonFile = new File(properties.getProperty("edit_kyc_status_id_path"));
        String response = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/json").
                body(jsonFile).
                     //  multiPart("file",jsonFile).
        when().
                put("/kyc/add/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String current_status = js.getString( "status" );
        Assert.assertEquals(current_status,properties.getProperty("kyc_status"));
    }

    //TC003-Editing the Kyc status
    @Test
    public void editKycStatus(){
        File jsonFile = new File(properties.getProperty("edit_kyc_status_id_path"));
        String response = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/json").
                body(jsonFile).
        when().
                put("/kyc/add/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String before_updating_status = js.getString( "status" );
        System.out.println("Before updating the KYC Status: "+before_updating_status);
        // After Updating status
        File jsonFiles = new File(properties.getProperty("edit_kyc_status_path"));
        String res = given().
                baseUri(properties.getProperty("base_URL")).
                header("content-type", "application/json").
                body(jsonFiles).
        when().
                put("/kyc/add/1").
        then().
                assertThat().
                statusCode(200).extract().response().asString();
        JsonPath jp = new JsonPath(res);
        String after_updating_status = jp.getString( "status" );
        System.out.println("After updating the KYC Status: "+after_updating_status);
        Assert.assertEquals(after_updating_status,properties.getProperty("status_kyc"));
    }

    //TC002-Getting the Kyc status of an employee
    @Test(priority = 10 )
    public void kycStatusEmployeeByID(){
        String response =  given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app").
                when().
                get("/kyc/get/"+properties.getProperty("user_id")).
                then().
                statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String value = js.getString( "userId.id" );
        Assert.assertEquals(properties.getProperty("user_id"),value);
    }

    // TC001-Validating the Json Schema for the Kyc status of an employee
    @Test(priority = 11)
    public void jsonValidatorForKycStatusfEmployee(){
        given().
                baseUri("https://kyc-backend-urtjok3rza-wl.a.run.app").
        when().
                get("/kyc/get/1").
        then().
                body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/json/kyc_status_employee.json")));
    }
}


