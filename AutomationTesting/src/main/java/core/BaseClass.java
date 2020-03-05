package core;

import automationtools.SeleniumDriver;
import entities.DataRow;

public class BaseClass {

    public static SeleniumDriver seleniumDriver;
    private static String reportDirectory;
    public static String testName;

    public static void setReportDirectory(String dir)
    {
        reportDirectory = dir;
    }

    public static String getReportDirectory()
    {
        return reportDirectory;
    }

   public String dataPath;



}
