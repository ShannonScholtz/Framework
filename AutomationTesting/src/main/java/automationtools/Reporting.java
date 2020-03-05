package automationtools;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import core.BaseClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends BaseClass {

    private static ExtentReports reports;
    private static ExtentTest currentTest;

    private static void setup(){

        setReportDirectory(System.getProperty("user.dir") + "\\Reports\\"+testName + "\\" + getCurrentTime() + "\\");
        new File(getReportDirectory()).mkdirs();
        reports = new ExtentReports();
        ExtentHtmlReporter html = new ExtentHtmlReporter(getReportDirectory() + "ExtentReport.html");
        reports.attachReporter(html);
        reports.setAnalysisStrategy(AnalysisStrategy.TEST);

        reports.flush();
    }

    public static void createTest()
    {
        if(reports == null)
        {
            setup();
        }

        if(currentTest == null || !currentTest.getModel().equals(testName))
        {
            currentTest = reports.createTest(testName);
        }
    }

    public static void stepPassed(String message)
    {
        if(currentTest == null)
        {
            createTest();
        }

        currentTest.pass(message);
        System.out.println("[SUCCESS] - " + message);

        reports.flush();

    }

    public static void info(String message)
    {
        if(currentTest == null)
        {
            createTest();
        }

        currentTest.info(message);
        System.out.println("[INFO] - " + message);

        reports.flush();

    }

    public static void warning(String message)
    {
        if(currentTest == null)
        {
            createTest();
        }

        currentTest.warning(message);
        System.out.println("[WARNING] - " + message);

        reports.flush();

    }



    public static void stepPassedWithScreenshot(String message)
    {
        if(currentTest == null)
        {
            createTest();
        }
        try {
                currentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(seleniumDriver.takeScreenShot(true)).build());

        }catch (Exception e)
        {
            currentTest.pass(message + "- screenshot failed to capture");
        }

        System.out.println(message);

        reports.flush();
    }

    public static String testFailed(String message)
    {
        if(currentTest == null)
        {
            createTest();
        }

        try{
            currentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(seleniumDriver.takeScreenShot(false)).build());

        } catch(Exception e)
        {
            currentTest.fail(message + "- screenshot failed to capture");
        }

        return message;
    }


    public static String testFailed()
    {
        if(currentTest == null)
        {
            createTest();
        }

        try{
            currentTest.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(seleniumDriver.takeScreenShot(false)).build());

        } catch(Exception e)
        {
            currentTest.fail("- screenshot failed to capture");
        }

        return "";
    }


    public static String finaliseTest()
    {
        if(currentTest == null)
        {
            createTest();
        }
        try {
            currentTest.pass("Test Complete - ", MediaEntityBuilder.createScreenCaptureFromPath(seleniumDriver.takeScreenShot(true)).build());

        }catch (Exception e)
        {
            currentTest.pass("Test Complete");
        }

        System.out.println("COMPLETED - Test Complete");

        reports.flush();

        return null;
    }

    public static String getCurrentTime()
    {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");

        return simpleDateFormat.format(date);
    }

}
