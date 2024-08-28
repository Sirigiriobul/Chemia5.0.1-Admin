package usersCreation;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import models.Header;

//import CHEMIASOFT.Chemia.Utilites.CaptureScreenShot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SampleTest {
	WebDriver driver;
	Utililities util;
	AdminPage adminPage=null;
	HomePage hm = null;
	//To write final status on the excel sheet
	int RowNumber = 1;
	// Extent Reports
		public ExtentSparkReporter spark;
		public ExtentReports extent;
		public ExtentTest test;
		public Properties property;
		public FileInputStream fis;
		public String path ="C:\\Users\\swathi.boda\\eclipse-workspace\\Test1\\src\\test\\resources\\testdata.properties";

		private static Map<String, Header> allData; 
	@BeforeSuite
	public void beforetest() throws Exception {

	
		// Formating the data
		String date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
		String pathOfReport = Paths.get("reports").toAbsolutePath().toString()	;	// creates one extent report on the given path
		this.spark = new ExtentSparkReporter(pathOfReport+ "/" + date + " " + "Report.html");
		// Setting title for the document
		spark.config().setDocumentTitle("Create Users Automation Report");// title of the report
		// Setting name of the report
		spark.config().setReportName("Create multiple Users");// name of the report
		// Selecting the theme
		spark.config().setTheme(Theme.DARK);

		// Creates the report
		this.extent = new ExtentReports();
		// Attaches the report
		extent.attachReporter(this.spark);
		allData = new HashMap<>();
		
		// Extra information to write on the report
//		extent.setSystemInfo("Browser", this.Myutility.getDataFromProparties("browser"));
//		extent.setSystemInfo("Host", this.Myutility.getDataFromProparties("host"));
//		extent.setSystemInfo("os", this.Myutility.getDataFromProparties("os"));
//		extent.setSystemInfo("Tester Name", this.Myutility.getDataFromProparties("tester"));
//		extent.setSystemInfo("URL", this.Myutility.getDataFromProparties("URL"));
	}
	
	@BeforeTest
	public  void browserInitilization() throws IOException {
		
			 property = new Properties();
			//String path = Paths.get("testdata.properties").toAbsolutePath().toString()	;	
			
			fis = new FileInputStream(path);
			property.load(fis);
			System.out.print(property.getProperty("browser"));
			
		String browser_in =property.getProperty("browser");
		String app_url =property.getProperty("url");
		
		// Extra information to write on the report

		extent.setSystemInfo("Browser", browser_in);
		extent.setSystemInfo("URL", app_url);
		extent.setSystemInfo("os", property.getProperty("os"));
		extent.setSystemInfo("Tester Name",property.getProperty("tester"));
		extent.setSystemInfo("Host", property.getProperty("host"));

//		hm.browserInitilization(browser_in);
		
				switch (browser_in.toLowerCase()) {
		        case "chrome":
		           // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
		            driver = new ChromeDriver();
		            break;
		        
		        case "firefox":
		            //System.setProperty("webdriver.gecko.driver", "");
		            driver = new FirefoxDriver();
		            break;
		        
		        case "edge":
		           // System.setProperty("webdriver.edge.driver", "/path/to/msedgedriver");
		            driver = new EdgeDriver();
		            break;
		        
		        case "safari":
		            // SafariDriver is bundled with macOS, no need to set the system property
		            driver = new SafariDriver();
		            break;
		        
		        default:
		            System.out.println("Browser type not supported.");
		            
		            // skip the rest of the loop and continue with the next browser
				}
				
		
}
	@AfterTest
	// In after Test we flush out the report for saving all the data
	public void afterTest() {

		extent.flush();
	}
	@AfterMethod
	public void AfterMethod(ITestResult result) throws Exception {
		util = new Utililities(); 
		int SheetNumber = util.getSheetNumber(property.getProperty("sheetName"));
		System.out.print("SheetNumber------------->"+SheetNumber);
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " Test Case is Passed ");

		}
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + " Test Case is Failed  ");
			test.log(Status.FAIL, "Test Case is Faild due to  " + result.getThrowable());
		}
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, " Test Case Skiped is " + result.getName());

		}
	}
@Test(priority = 0)
public void launchAndLoginToApp() throws Throwable {
			
			property = new Properties();
			fis = new FileInputStream(path);
			hm = new HomePage(driver);
			this.property.load(fis);
			hm = new HomePage(driver);
			String app_url =property.getProperty("url");
			String app_user =property.getProperty("username");
			String app_password =property.getProperty("password");
			String app_title =property.getProperty("title");
			String app_site =property.getProperty("sitename");
			
			// Creating the test
			this.test = extent.createTest("Launch and Login to Apllication");
			
			// Logging to the report as a node -1
			ExtentTest parentNode1 = test.createNode("Launch and Login Login to Application");
			hm.launchApplication(app_url,app_title,app_site);
			
			// Giving info to the node Parent Node-1
			parentNode1.log(Status.PASS, "Launching the URL " + app_url);
			
			hm.loginToApllication(app_user,app_password);
			parentNode1.log(Status.PASS, "login To Apllication with user and PWD are: "+app_user+", "+app_password);
			//    driver.close();
//			try {
//			hm.isMultiloginPopupDisplayd();
//			hm.clickMultipleLoginPopupYesButton();
//			hm.loginToApllication(app_user,app_password);
//			}catch (Exception e) {
//				System.out.println("Multiple Login Popup Is not displayed..");
//			}
			
//			hm.clickOnAdmin();
//			parentNode1.log(Status.PASS, "Clicked on Admin");
//			String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Login to App");
//			test.addScreenCaptureFromPath(screenshortpath);		
}
@Test(priority = 1)
		public void Adduser() throws Exception {
			this.test = extent.createTest("Creating users");
		
			ExtentTest parentNode1 = test.createNode("Creating multiple users");
			adminPage = new AdminPage(driver);
			hm = new HomePage(driver);
	
			hm.clickOnAdmin();
			parentNode1.log(Status.PASS, "Clicked on Admin");
			adminPage.navigateToAdduserForm(test);
			parentNode1.log(Status.PASS, "Navigated to Add users form.");
			util = new Utililities();
			int sheetNumber = util.getSheetNumber(property.getProperty("sheetName"));
			
			int rowCount = util.getRowCount(sheetNumber);
			System.out.println("rowCount"+rowCount);
			 for (int i = 1; i <= rowCount; i++) {
				adminPage.clickOnAddInManageUser();
				parentNode1.log(Status.PASS, "Clicked on Add in Manage Users Page");
				 Map<String, Object> rowData=util.prepareRowData(i, sheetNumber);
		
				//String userName =adminPage.getUserName(i, sheetNumber).toString();
				//adminPage.enterDetailsInAddUserForm(i,sheetNumber,test);
				 
				 String userName = (String) rowData.get("User Name");
				 
				 adminPage.enterDetailsInAddUserForm1(rowData,test);
				parentNode1.log(Status.PASS, "Entered Details In Add user form");
				
		
				try {
					
		//			adminPage.clickOnCancelInCreateUser();
		//			parentNode1.log(Status.PASS, "Clicked on Cancel in Create Add Users Page");
				adminPage.clickOnsaveInCreateUser();
				parentNode1.log(Status.PASS, "Clicked on Save in Create Add Users Page");
				
				String actual_msg=adminPage.isScuccessfulMessageDIsplayed();
				System.out.println("Actual Confirmation message is : "+actual_msg);
				String expected ="User details for "+userName+" is added successfully.";
				if(actual_msg.endsWith("successfully."))
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.addUser_status, "Pass");
				else {
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.addUser_status, "Fail");
					String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Create User Failed "+userName);
					test.addScreenCaptureFromPath(screenshortpath);	
				}
		
				}catch (Exception e) {
					parentNode1.log(Status.FAIL, e);
					String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Create User Failed "+userName);
					test.addScreenCaptureFromPath(screenshortpath);
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.addUser_status, "Fail");
		
				}
				//adminPage.refreshpage();
				Thread.sleep(3000);
			 }	//for
		
		
}
@Test(priority = 2)
	public void addUsersToDepartments() throws Exception {
		adminPage = new AdminPage(driver);
		this.test = extent.createTest("Adding Users to Departments");
		ExtentTest parentNode1 = test.createNode("Adding Users to Respective Departments");
		adminPage.refreshpage();
		hm = new HomePage(driver);
		
//		hm.clickOnAdmin();
//		parentNode1.log(Status.PASS, "Clicked on Admin");

		adminPage.navigateToDapartmentrolesPage(test);
		parentNode1.log(Status.PASS, "Navigated to Department Add users form.");
		util = new Utililities();
	
		int sheetNumber = util.getSheetNumber(property.getProperty("sheetName"));
		
		int rowCount = util.getRowCount(sheetNumber);
		System.out.println("rowCount "+rowCount);
		 for (int i = 1; i <= rowCount; i++) {
			 Map<String, Object> rowData=util.prepareRowData(i, sheetNumber);
				//adminPage.addUserToDepartment(sheetNumber,i,property.getProperty("sitename"),test);
			 String status = (String) rowData.get("Add User Status");
	
			 if(status.equalsIgnoreCase("Pass")) {
				adminPage.addUserToDepartment1(rowData,test);
	
				parentNode1.log(Status.PASS, "Added user details to Department");
				adminPage.clickOnsaveInAddUserToDep();
				parentNode1.log(Status.PASS, "Clicked on Save in Create Add Users Page");
				String actual_msg=adminPage.isScuccessfulMessageDIsplayed();
				try {
				if(actual_msg.endsWith("successfully.")) {
					parentNode1.log(Status.PASS, "Added user to Respective Department Succesfully.");
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Fail");
					}
				else
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Fail");
				
	//			adminPage.clickOnCancelInAddUserToDep();
	//			parentNode1.log(Status.PASS, "Clicked on Cancel in Create Add Users Page");
			 }catch (Exception e) {
					parentNode1.log(Status.FAIL, e);
					String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Add User to Departments Failed");
					test.addScreenCaptureFromPath(screenshortpath);
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Fail");
	
				}
				}//if
			 else
				 System.out.println("Add user status: "+status+" Unable to add user to department");
				
				adminPage.refreshpage();
				Thread.sleep(2000);
		 }//for
		 
}
	
@Test(priority = 3)

	public void verifyUserCreation() throws Exception {
		adminPage = new AdminPage(driver);
		this.test = extent.createTest("Verifying Users Creation: ");
		ExtentTest parentNode1 = test.createNode("Verifying users creaton in respective departments: ");
	
		adminPage.navigateToDapartmentrolesPage(test);
		adminPage.refreshpage();
		parentNode1.log(Status.PASS, "Navigated to Department users form.");
		util = new Utililities();
		int sheetNumber = util.getSheetNumber(property.getProperty("sheetName"));
		
		int rowCount = util.getRowCount(sheetNumber);
		System.out.println("rowCount"+rowCount);
		 for (int i = 1; i <= rowCount; i++) {
			 Map<String, Object> rowData=util.prepareRowData(i, sheetNumber);
			 
			 String expect_userName = (String) rowData.get("User Name");
			 String Department = (String) rowData.get("Select Department");
			 String subDepartment = (String) rowData.get("Select sub Department");
			 
			 //String expect_userName= util.getDataFromXl(sheetNumber, i,adminPage.userName_colum).trim();
			 adminPage.navigateToRelatedDepartment(Department, subDepartment, test);
			 adminPage.clickOnSearchUser(expect_userName);
				parentNode1.log(Status.PASS, "Sarching for user name : "+expect_userName);
				try {
			 String actual_Username=adminPage.getUserName();
			 if(expect_userName.equalsIgnoreCase(actual_Username)) {
					parentNode1.log(Status.PASS, "User creation and adding completed for User : "+expect_userName);
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Pass");
	
			 }//if
			 else
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Fail");
	
				}
				catch(Exception e) {
					System.out.println("Exception "+e);
					util.writeOnFTSexcelFile(sheetNumber, i, adminPage.final_status, "Fail");
	
					parentNode1.log(Status.FAIL, "User is not created"+expect_userName);
					String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Add User to Departments Failed");
					test.addScreenCaptureFromPath(screenshortpath);
				}
		 }//for
}//verifyUserCreation


@DataProvider(name = "loginData")
public Object[][] loginDataProvider() throws IOException {
	util = new Utililities();
    return util.readExcelData("LoginDetails");
}
//@AfterMethod(dependsOnGroups = { "group1" })
	public void afterMethod(ITestResult result) throws Throwable {
	    System.out.println("After method after test login");
		adminPage = new AdminPage(driver);
	
	    if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " Created user login successful ");
			String screenshortpath = CaptureScreenShot.GetScreenShort(driver, result.getName());
			test.addScreenCaptureFromPath(screenshortpath);
			util.writeOnFTSexcelFile(0, RowNumber, adminPage.login_final_status, "Pass");
	
	
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + " Created user login is Failed  ");
			test.log(Status.FAIL, "Created user login Faild due to  " + result.getThrowable());
			String screenshortpath = CaptureScreenShot.GetScreenShort(driver, result.getName());
			test.addScreenCaptureFromPath(screenshortpath);
			util.writeOnFTSexcelFile(0, RowNumber, adminPage.login_final_status, "Fail");
	
	
		}
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, " Created user login Skiped " + result.getName());
			String screenshortpath = CaptureScreenShot.GetScreenShort(driver, result.getName());
			test.addScreenCaptureFromPath(screenshortpath);
			util.writeOnFTSexcelFile(0, RowNumber, adminPage.login_final_status, "Skip");
	
	
		}
		this.RowNumber += 1;
	    
}
@Test(dataProvider = "loginData",priority = 4)
	public void setupLoginPwd(Object username, Object password) throws Throwable {
	    
	  //  System.out.println("Username: " + username + ", Password: " + password);
		property = new Properties();
		fis = new FileInputStream(path);
		hm = new HomePage(driver);
		this.property.load(fis);
		adminPage = new AdminPage(driver);
		String app_url =property.getProperty("url");
		String app_title =property.getProperty("title");
		String app_site =property.getProperty("sitename");
		String default_pwd ="Chemia@123";
		// Creating the test
		this.test = extent.createTest("Setup User Login:"+username);
		try {
			hm.logOutApplication();
	
		}catch(Exception e){
			 System.out.println("Application already loged out.");
	
		}
		// Logging to the report as a node -1
		ExtentTest parentNode1 = test.createNode("Launch and Login Login to Application with user: "+username);
		hm.launchApplication(app_url,app_title,app_site);
		
		// Giving info to the node Parent Node-1
		parentNode1.log(Status.PASS, "Launching the URL " + app_url);
		
		hm.loginToApllication((String) username,default_pwd);
		parentNode1.log(Status.PASS, "login To Apllication with user and PWD are: "+username+", "+default_pwd);
		try {
			hm.clickOkOnDesclaimerForm();
			parentNode1.log(Status.PASS, "Accepted DesclaimerForm");
	
		}catch(Exception e){
			 System.out.println("Disclaimer form not activated user already agreed terms and conditions.");
		}
		try {
		hm.changePassword(default_pwd, (String) password);
		parentNode1.log(Status.PASS, "Created to new password:"+((String) password));
		}
		catch(Exception e){
			System.out.println("Change new password window is not there as already pass word changed ");
			parentNode1.log(Status.PASS, "Created to new password:"+((String) password));
	
		}
		Thread.sleep(2000);
		hm.setupSecurQtns();
		parentNode1.log(Status.PASS, "Security questions setup completed");
		
	
}

@Test(dataProvider = "loginData",priority =5 )
public void loginFinalStatus(Object username, Object password) throws Throwable {
	property = new Properties();
	fis = new FileInputStream(path);
	hm = new HomePage(driver);
	this.property.load(fis);
	hm = new HomePage(driver);
	adminPage = new AdminPage(driver);

	String app_url =property.getProperty("url");
	String app_title =property.getProperty("title");
	String app_site =property.getProperty("sitename");
	
	// Creating the test
	this.test = extent.createTest("Launch and Login to Apllication");
	
	// Logging to the report as a node -1
	ExtentTest parentNode1 = test.createNode("Launch and Login Login to Application");
	hm.launchApplication(app_url,app_title,app_site);
	
	hm.loginToApllication((String) username,(String) password);
	if(hm.isDashBoardExist()) {
	parentNode1.log(Status.PASS, "Successfully login to the application with user "+((String) username)+" with password"+((String) password));
	String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Sucessfully login with the created User");
	test.addScreenCaptureFromPath(screenshortpath);
	util.writeOnFTSexcelFile(0, RowNumber, adminPage.login_final_status, "Pass");
	}
	else {
		String screenshortpath = CaptureScreenShot.GetScreenShort(driver, "Unable to login with the created User");
		test.addScreenCaptureFromPath(screenshortpath);
		parentNode1.log(Status.FAIL, "Unble to login with the user: "+((String) username)+" with password"+((String) password));
		util.writeOnFTSexcelFile(0, RowNumber, adminPage.login_final_status, "Fail");
	}
	this.RowNumber += 1;
}//loginFinalStatus

// In after suite we quite the driver. That means we close the browser
@AfterSuite
public void afterSuite() {
	System.out.println("After suit: closing the Browser...");
	hm = new HomePage(driver);
	hm.logOutApplication();
	this.driver.quit();
}

}
