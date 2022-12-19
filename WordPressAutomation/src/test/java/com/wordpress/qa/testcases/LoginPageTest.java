/**
 * 
 */
package com.wordpress.qa.testcases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wordpress.qa.base.TestBase;
import com.wordpress.qa.util.ExtentClass;
import com.worpress.qa.pages.HomePage;
import com.worpress.qa.pages.LoginPage;

/**
 * @author Hitendra--
 *
 */
public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	
	
	public LoginPageTest() {
		super();
	}

	@BeforeTest(groups = { "Smoke", "Regression","SytemTest" })
	public void startTest() {
		ExtentClass.setExtent();
	}

	@BeforeMethod(groups = { "Smoke", "Regression","SytemTest" })
	public void setup() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(groups = { "Smoke","SytemTest" })
	public void loginPageTitleTest() {
		ExtentClass.extentTest = ExtentClass.extent.startTest("VerifyloginPageTitleTest");
		log.debug("loginPageTitleTest start");
		log.info("Login");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Log In � opensourcecms � WordPress");
		log.debug("loginPageTitleTest ends");
	}

	@Test(groups = { "Regression","SytemTest" })
	public void loginWordPresslogoTest() throws Throwable {
		log.debug("loginWordPresslogoTest start");
		ExtentClass.extentTest = ExtentClass.extent.startTest("VerifyloginWordPresslogoTest");
		boolean flag = loginPage.logo();
		Assert.assertTrue(flag);
		log.debug("loginWordPresslogoTest ends");
	}

	@Test(groups = { "Smoke","SytemTest" })
	public void loginTest() throws Throwable {
		log.debug("loginTest Start");
		ExtentClass.extentTest = ExtentClass.extent.startTest("VerifyloginTest");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.postLinkIsdisplayed());
		log.debug("loginTest ends");
	}

	@AfterMethod(groups = { "Smoke", "Regression","SytemTest" })
	public void tearDown(ITestResult result) throws IOException {
		ExtentClass.setResult(driver, result);
		driver.quit();
	}

	@AfterTest(groups = { "Smoke", "Regression","SytemTest" })
	public void endTest() {
		ExtentClass.endExtent();
		ExtentClass.closeExtent();
	}
}
