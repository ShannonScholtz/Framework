package testing.pageobjects;

import org.openqa.selenium.By;

public class FirstSeleniumPageObjects {

    public static String url() {
        return "https://www.ilabquality.com/";
    }

    public static By LandingPageValidation(String text) {
        return By.xpath("//img[@alt='" + text + "']");
    }

    public static By TabSelection(String text) {
        return By.xpath("//nav[@class='navigation-right text-right']//a[text()='" + text + "']");
    }

    public static By careerPageValidation(String text) {
        return By.xpath("//p[text()='WORK WITH']/span[contains(text(),'" + text + "')]");
    }

    public static By CareersParagraphValidation(String text) {
        return By.xpath("//p[contains(text(),'" + text + "')]");
    }

    public static By country(String text) {
        return By.xpath("//a[text()='" + text + "']");
    }

    public static By currentOpenings() {
        return By.xpath("//h3[text()='CURRENT OPENINGS']");
    }

    public static By firstJob() {
        return By.xpath("//div[@class='wpjb-grid-row wpjb-click-area  wpjb-free wpjb-type-full-time wpjb-category-south-africa'][1]//a[1]");
        //"//div[@class='wpjb-grid-row wpjb-click-area  wpjb-free wpjb-type-full-time wpjb-category-south-africa'][1]//span[@class='wpjb-line-major']/a");    
    }

    public static By jobHeadingValidation() {
        return By.xpath("//div[@class='container']/h1");
        //"//div[@class='wpjb-grid-row wpjb-click-area  wpjb-free wpjb-type-full-time wpjb-category-south-africa'][1]//span[@class='wpjb-line-major']/a");    
    }

    public static By headerTitleValidation(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]");
        //"//div[@class='wpjb-grid-row wpjb-click-area  wpjb-free wpjb-type-full-time wpjb-category-south-africa'][1]//span[@class='wpjb-line-major']/a");    
    }

    public static By apply() {
        return By.xpath("//a[text()='Apply Online ']");
    }

    public static By applicationInformation(String text) {
        return By.xpath("//label[contains(text(),'" + text + "')]/..//input");
    //Your name
        //Your e-mail address
        //Phone 
    }

    public static By motivation() {
        return By.xpath("//label[contains(text(),'Why would you like to work at iLAB?')]/..//textarea");

    }

    public static By ErrorValidation(String text) {
        return By.xpath("//li[text()='" + text + "']");

    }

    public static By sendButton() {
        return By.xpath("//input[@value='Send Application']");
    }

}
