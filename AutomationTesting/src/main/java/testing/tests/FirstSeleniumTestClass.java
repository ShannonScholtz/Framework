package testing.tests;

import automationtools.Reporting;
import core.BaseClass;
import entities.DataRow;
import java.util.Random;
import testing.pageobjects.FirstSeleniumPageObjects;

public class FirstSeleniumTestClass extends BaseClass {

    public static int num1, num2, num3;

    public static String ExecuteTest(DataRow values) {

        if (!Navigation(values.getColumnValue("TabSelection"), values.getColumnValue("Country"), values.getColumnValue("CompanyName"), values.getColumnValue("SentenceValidation"))) {
            return Reporting.testFailed();
        }

        if (!JobApplication(values.getColumnValue("Name"), values.getColumnValue("EmailAddress"), values.getColumnValue("JobMotivation"), values.getColumnValue("UploadFileValidation"))) {
            return Reporting.testFailed();
        }

        return Reporting.finaliseTest();

    }

    public static Boolean Navigation(String tab, String countrySelection, String companyName, String sentenceValidation) {

        if (!seleniumDriver.navigate(FirstSeleniumPageObjects.url())) {
            Reporting.testFailed("Failed to navigate to ilAB website");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.LandingPageValidation(companyName))) {
            Reporting.testFailed("Failed to wait for the company name");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.TabSelection(tab))) {
            Reporting.testFailed("Failed to wait for the correct tab");
            return false;
        }

        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.clickElement(FirstSeleniumPageObjects.TabSelection(tab))) {
            Reporting.testFailed("Failed to click on the correct tab");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.careerPageValidation(companyName))) {
            Reporting.testFailed("Failed to wait for the 'WORK WITH iLAB' heading");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.CareersParagraphValidation(sentenceValidation))) {
            Reporting.testFailed("Failed to wait for the starting sentence");
            return false;
        }

        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.scrollToElement(FirstSeleniumPageObjects.country(countrySelection))) {
            Reporting.testFailed("Failed to scroll to the correct country");
            return false;
        }

        seleniumDriver.pause(1000);
        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.clickElement(FirstSeleniumPageObjects.country(countrySelection))) {
            Reporting.testFailed("Failed to click on the correct country");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.currentOpenings())) {
            Reporting.testFailed("Failed to wait for the 'Current Openings' heading");
            return false;
        }

        seleniumDriver.takeScreenShot(true);

        String job = seleniumDriver.retreiveText(FirstSeleniumPageObjects.firstJob());

        if (job.isEmpty()) {
            Reporting.testFailed("Failed due to job being empty");
            return false;
        }

        if (!seleniumDriver.clickElement(FirstSeleniumPageObjects.firstJob())) {
            Reporting.testFailed("Failed to click on the first job available");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.headerTitleValidation(companyName))) {
            Reporting.testFailed("Failed to wait for the job title");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.jobHeadingValidation())) {
            Reporting.testFailed("Failed to wait for the job title");
            return false;
        }

        seleniumDriver.takeScreenShot(true);

        String jobValidation = seleniumDriver.retreiveText(FirstSeleniumPageObjects.jobHeadingValidation());

        if (job.equals(jobValidation)) {
            Reporting.testFailed("Failed due to the job selectedn not being correct");
            return false;
        }

        if (!seleniumDriver.scrollToElement(FirstSeleniumPageObjects.apply())) {
            Reporting.testFailed("Failed to click career tab");
            return false;
        }

        seleniumDriver.pause(1000);
        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.clickElement(FirstSeleniumPageObjects.apply())) {
            Reporting.testFailed("Failed to click career tab");
            return false;
        }

        return true;
    }

    public static Boolean JobApplication(String name, String emailAddress, String motivation, String UploadErrMessage) {

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.applicationInformation("Your name"))) {
            Reporting.testFailed("Failed to wait for the name label and textbox ");
            return false;
        }

        if (!seleniumDriver.scrollToElement(FirstSeleniumPageObjects.motivation())) {
            Reporting.testFailed("Failed to scroll to the ilab job application motivation");
            return false;
        }

        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.enterText(FirstSeleniumPageObjects.applicationInformation("Your name"), name)) {
            Reporting.testFailed("Failed to enter the name");
            return false;
        }

        if (!seleniumDriver.enterText(FirstSeleniumPageObjects.applicationInformation("Your e-mail address"), emailAddress)) {
            Reporting.testFailed("Failed to enter the e-mail address");
            return false;
        }

        Random generator = new Random();

        num1 = 0;
        num2 = generator.nextInt(5 - 9); //randomize to 8 becuase 0 counts as a number in the generator
        num3 = generator.nextInt(0 - 4);

        String number = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);

        if (!seleniumDriver.enterText(FirstSeleniumPageObjects.applicationInformation("Phone "), number)) {
            Reporting.testFailed("Failed to enter the cellphone number");
            return false;
        }

        if (!seleniumDriver.enterText(FirstSeleniumPageObjects.motivation(), motivation)) {
            Reporting.testFailed("Failed to enter the reason why one deserves the job");
            return false;
        }

        seleniumDriver.pause(2500);
        seleniumDriver.takeScreenShot(true);

        if (!seleniumDriver.scrollToElement(FirstSeleniumPageObjects.sendButton())) {
            Reporting.testFailed("Failed to scroll to the send application button");
            return false;
        }

        if (!seleniumDriver.clickElement(FirstSeleniumPageObjects.sendButton())) {
            Reporting.testFailed("Failed to click the send application button");
            return false;
        }

        if (!seleniumDriver.waitForElement(FirstSeleniumPageObjects.ErrorValidation(UploadErrMessage))) {
            Reporting.testFailed("Failed to wait for the upload file error message");
            return false;
        }

        return true;
    }
}
