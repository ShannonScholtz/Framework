import core.BaseClass;
import entities.DataRow;
import org.junit.jupiter.api.*;
import testing.tests.FirstSeleniumTestClass;
import automationtools.ExcelReader;
import automationtools.Reporting;
import automationtools.SeleniumDriver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstSelenium extends BaseClass {

    @BeforeEach
    public void init(TestInfo testInfo) {
        testName = testInfo.getDisplayName();
        Reporting.createTest();
        seleniumDriver = new SeleniumDriver(SeleniumDriver.BrowserType.CHROME);
    }

    @AfterEach
    public void close() {
        seleniumDriver.closeDriver();
    }

    @TestFactory
    Collection<DynamicTest> permTests() {
        List<DynamicTest> tests = new ArrayList<>();

        dataPath = System.getProperty("user.dir") + "\\data\\dataset1.xlsx";
        List<DataRow> data = ExcelReader.GetDataSet(dataPath, "Sheet1");

        for (DataRow row : data) {
            String name = row.getColumnValue("TabSelection");
            tests.add(DynamicTest.dynamicTest(name + " Test", () -> Test(row)));

        }
        return tests;
    }

    @Test
    public void Test(DataRow values) {
        String result = FirstSeleniumTestClass.ExecuteTest(values);
        assertTrue(result == null, result);
    }

}
