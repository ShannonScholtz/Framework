package automationtools;

import core.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class SeleniumDriver extends BaseClass {

    private WebDriver driver;
    private static int screenshotCounter = 0;

    public enum BrowserType {

        CHROME, IE, FIREFOX
    }

    private BrowserType currentBrowser;

    public SeleniumDriver(BrowserType browser) {
        this.currentBrowser = browser;
        launchDriver();
    }

    public boolean launchDriver() {
        switch (this.currentBrowser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                this.driver = new InternetExplorerDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            default:
                break;
        }

        this.driver.manage().window().maximize();
        return true;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean closeDriver() {
        try {
            this.driver.close();
            this.driver.quit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void pause(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean waitForElement(By selector) {
        boolean found = false;
        int counter = 0;
        try {
            while (!found && counter < 30) {
                try {
                    WebDriverWait wait = new WebDriverWait(this.driver, 30);
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
                    found = true;
                } catch (Exception e) {
                    counter++;
                    pause(1000);
                }
            }
            return found;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean waitForElement(By selector, int seconds) {
        boolean found = false;
        int counter = 0;
        try {
            while (!found && counter < 30) {
                try {
                    WebDriverWait wait = new WebDriverWait(this.driver, seconds);
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
                    found = true;
                } catch (Exception e) {
                    counter++;
                    pause(1000);
                }
            }
            return found;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickElement(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement webElementClick = this.driver.findElement(selector);
            webElementClick.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterText(By selector, String text) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement webElementClick = this.driver.findElement(selector);
            webElementClick.sendKeys();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean navigate(String text) {
        try {
            this.driver.navigate().to(text);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String retreiveText(By selector) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement getElement = this.driver.findElement(selector);
            return getElement.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean ValidateText(By selector, String text) {
        try {
            waitForElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement getElement = this.driver.findElement(selector);
            return getElement.getText().equals(text);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String takeScreenShot(boolean status) {
        screenshotCounter++;
        StringBuilder imagePathBuilder = new StringBuilder();
        StringBuilder relativePathBuilder = new StringBuilder();

        try {
            imagePathBuilder.append(getReportDirectory());
            relativePathBuilder.append("ScreenShots\\");
            new File(imagePathBuilder.toString() + relativePathBuilder.toString()).mkdirs();

            relativePathBuilder.append(screenshotCounter + " ");
            if (status) {
                relativePathBuilder.append("PASSED");
            } else {
                relativePathBuilder.append("FAILED");
            }

            relativePathBuilder.append(".png");

            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(imagePathBuilder.append(relativePathBuilder).toString()));

            return "./" + relativePathBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean scrollToElement(By selector) {
        try {
            waitForElement(selector);
            WebElement element = this.driver.findElement(selector);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            pause(500);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //Narrator.logError("Error scrolling to element - " + elementXpath + " - " + e.getStackTrace());
            // this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public boolean validateContainsTextIgnoreCase(String text1, String text2) {
        try {
            if (!text1.toLowerCase().contains(text2.toLowerCase())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            //Narrator.logError("Failed to Validate Text - " + e.getMessage());
            //this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public Boolean fileUpload(By selector, String fileName) {
        try {
            String filePath = "C:/AutomationTesting/" + fileName;
            WebElement webElement = driver.findElement(selector);

            //pause(10000);
            webElement.sendKeys(filePath);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
