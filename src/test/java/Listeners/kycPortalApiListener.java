package Listeners;

import PreRequisites.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class kycPortalApiListener extends BaseClass implements ITestListener {

    ExtentSparkReporter myReporter;
    ExtentReports extentReports;
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        test.log(Status.INFO, result.getName()+" Started ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getName() + ": Succeeded");
        test.pass(result.getName()+": Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getName() + ": Failed");
        test.fail(result.getName()+": Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        //initialize the reporter
        log.info("Extent Report");
        myReporter=new ExtentSparkReporter("results/Reports/extentReport.html");
        extentReports =new ExtentReports();
        extentReports.attachReporter(myReporter);
        test = extentReports.createTest("KYC API Testing");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
