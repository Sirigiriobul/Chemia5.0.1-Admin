package usersCreation;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
	 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	 public HomePage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		}
	 
	 	@FindBy(xpath = "//input[@type='text']")
		WebElement compName;
		@FindBy(xpath = "//button[contains(text(),'Next')]")
		WebElement nextButton;
		
		@FindBy(xpath = "//app-dashboard//div[@id='dashDivId']")
		WebElement dashBoard;
	 
	 	@FindBy(xpath = "//input[@name='username']")
		WebElement UserName; // user name text field
	 	
		@FindBy(xpath = "//input[@name='password']")
		WebElement UserPassword; // password text field
		
		@FindBy(xpath = "//div[@id='chngPswd']//div//input[@name='forceChnge.currentPswd']")
		WebElement Current_Password; // Current password text field
		
		@FindBy(xpath = "//div[@id='chngPswd']//div//input[@name='forceChnge.newPswd']")
		WebElement new_Password; // New password text field
		
		@FindBy(xpath = "//div[@id='chngPswd']//div//input[@name='forceChnge.reEnterPswd']")
		WebElement reEnternew_Password; // Re enter New password text field
		
		@FindBy(xpath = "//button[@id='subForceChangeId']//span[text()=' Submit ']")
		WebElement submitNewPwd_button; // Submit New password text field
		
		@FindBy(xpath = "//button[@id='cancelDisclamerButId']//span[text()=' Cancel ']")
		WebElement desclcancel_button; // Disclaimer Cancel text field
		
		@FindBy(xpath = "//body[@id='mainBody']//div[@class='container-fluid']")
		WebElement desclimaer_content; // Disclaimer page content
		
		@FindBy(xpath = "//button[@id='desclOkButtonId']//span[text()='I Accept ']")
		WebElement desclOk_button; // Disclaimer OK text field
		
		@FindBy(xpath = "//button[@id='submitSecAnsId']//span[text()=' Save ']")
		WebElement submitSecSave_button; // Security questions submit button
		
		@FindBy(xpath = "//button[@id='cancelSecAnsId']//span[text()=' Cancel ']")
		WebElement submitSecCancel_button; // Security questions cancel button
		
		@FindBy(xpath = "//button[contains(text(),login)]")
		 public WebElement LoginButton;// Login button
		
		@FindBy(xpath = "//p-dialog//span[text()='Multiple Login']")
		WebElement MultipleLoginPopup; // multiple login popup
		
		// yes button on the multiple login popup
		@FindBy(xpath = "//p-dialog//span[contains(text(),'Yes')]")
		WebElement MultipleLoginPopupYesButton;
		// No button on the multiple login popup
		
		@FindBy(xpath = "//span[contains(text(),'No')]")
		WebElement MultipleLoginPopupNoButton;
		
		@FindBy(xpath="//div//h5[text()='ADMIN']")
		WebElement Admin;
		
		@FindBy(xpath="//div[@id='myTopnav']//button[contains(.,' User ')]")
		WebElement user_button;
		
		@FindBy(xpath="//button[@id='loggoutButtonId']//span")
		WebElement logout_button;
		
		public boolean isDashBoardExist() {
			boolean flag;
			try {
			 flag=dashBoard.isDisplayed();
			}catch(Exception e) {
			flag=false;
			}
			System.out.println("falg: "+flag);

	        return flag;

		}//isDashBoardExist
		
	public void launchApplication(String app_url, String app_title, String app_site) throws Throwable {
		 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

	    
		driver.get(app_url);
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	   // driver.manage().window().minimize();  

	    driver.manage().window().maximize() ;  
	    wait.until(pageLoadCondition);
        wait.until(ExpectedConditions.titleIs(app_title));
        System.out.println("Page title is correct: " + driver.getTitle());

        wait.until(ExpectedConditions.elementToBeClickable(compName));
        compName.sendKeys(app_site);
        System.out.println("Enter Company site Name as : " +app_site);

        
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
        
       // Once the title is as expected, perform further actions
        System.out.println("Clicked on Next Button.");
        
        

	}
	ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String readyState = (String) js.executeScript("return document.readyState");
            return readyState.equals("complete");
        }
	};

	public void loginToApllication(String app_user, String app_password) {
		
		 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

	        wait.until(ExpectedConditions.elementToBeClickable(UserName));
	        UserName.clear();
	        UserName.sendKeys(app_user);
	        System.out.println("Enter User Name as : " +app_user);	
	        
	        wait.until(ExpectedConditions.elementToBeClickable(UserPassword));
	        UserPassword.clear();
	        UserPassword.sendKeys(app_password);
	        System.out.println("Enter Password as : " +app_password);	
	        
	        wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
	        LoginButton.click();
	        
	        System.out.println("Clicked on Login.");	
	        try {
				isMultiloginPopupDisplayd();
				clickMultipleLoginPopupYesButton();
				loginToApllication(app_user,app_password);
				}catch (Exception e) {
					System.out.println("Multiple Login Popup Is not displayed..");
				}

	}
	public void isMultiloginPopupDisplayd() {
		 wait.until(pageLoadCondition);
		 wait.until(ExpectedConditions.elementToBeClickable(MultipleLoginPopup));
	        System.out.println("Multilogin Popup Displayd");	

	}
	public void clickMultipleLoginPopupYesButton() {
	 	
	        wait.until(ExpectedConditions.elementToBeClickable(MultipleLoginPopupYesButton));
	        MultipleLoginPopupYesButton.click();
	        System.out.println("Clicked on Multiple Login Popup YesButton.");	
		
	}
	public void clickOkOnDesclaimerForm() throws InterruptedException {
        driver.manage().window().fullscreen();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(desclOk_button));
        desclOk_button.click();
        System.out.println("Clicked on Disclaimer form I Accept Button.");	
        driver.manage().window().maximize();

	}//clickOkOnDesclaimerForm
	
	public void clickOkCancelDesclaimerForm() {
        wait.until(ExpectedConditions.elementToBeClickable(desclcancel_button));
        desclcancel_button.click();
        System.out.println("Clicked on Disclaimer form Cancel Button.");	

	}//clickOkCancelDesclaimerForm
	
	public void changePassword(String defaultPwd, String newPwd) {
		
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.elementToBeClickable(Current_Password));
        Current_Password.clear();
        Current_Password.sendKeys(defaultPwd);
        
        wait.until(ExpectedConditions.elementToBeClickable(new_Password));
        new_Password.clear();
        new_Password.sendKeys(newPwd);
        
        wait.until(ExpectedConditions.elementToBeClickable(reEnternew_Password));
        reEnternew_Password.clear();
        reEnternew_Password.sendKeys(newPwd);
        
        driver.manage().window().fullscreen();
        wait.until(ExpectedConditions.elementToBeClickable(submitNewPwd_button));
        submitNewPwd_button.click();
        System.out.println("Clicked on Submit New Password Button.");
        driver.manage().window().maximize();

        
	}//changePassword
	
	@FindBy(xpath = "//div//ul//p-dropdownitem//li/span[text()='What high school did you attend?']")
	 public WebElement question_1;// security question1
	
	@FindBy(xpath = "//div//ul//p-dropdownitem//li/span[text()='What is the name of your first school?']")
	 public WebElement question_2;// security question2
	
	@FindBy(xpath = "//div//ul//p-dropdownitem//li/span[text()='What is your mothers maiden name?']")
	 public WebElement question_3;// security question3
	
	public void setupSecurQtns() throws InterruptedException {
       
		driver.manage().window().maximize();

		for(int i=1;i<=3;i++) {
		WebElement questionDropdown_trigger=driver.findElement(By.xpath("//table[@class='MatTableStyle']//tr["+i+"]//p-dropdown//span[contains(@class,'trigger-icon')]"));
		WebElement answerText_field=driver.findElement(By.xpath("//table[@class='MatTableStyle']//tr["+i+"]//td//input"));

		wait.until(ExpectedConditions.elementToBeClickable(questionDropdown_trigger));
        questionDropdown_trigger.click(); 
        Thread.sleep(2000);
        if(i==1)
        {wait.until(ExpectedConditions.elementToBeClickable(question_1));
        question_1.click();}
        else if(i==2)
        {wait.until(ExpectedConditions.elementToBeClickable(question_2));
        question_2.click();}
        else if(i==3)
        {
        wait.until(ExpectedConditions.elementToBeClickable(question_3));
        question_3.click();}
        
		wait.until(ExpectedConditions.elementToBeClickable(answerText_field));
		answerText_field.clear();
		answerText_field.sendKeys("Chemia");//Hard Coded the answer here


		
		}
        driver.manage().window().fullscreen();

		wait.until(ExpectedConditions.elementToBeClickable(submitSecSave_button));
		submitSecSave_button.click();
		
//		wait.until(ExpectedConditions.elementToBeClickable(submitSecCancel_button));
//		submitSecCancel_button.click();
        driver.manage().window().maximize();

	}//setupSecurQtns
	
	
	public void clickOnAdmin() {
		 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Admin));
		Admin.click();
        System.out.println("Clicked on Admin.");	

	}
	public void logOutApplication() {
		 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(user_button));
		user_button.click();
		System.out.println("Clicked on User in topbar Icon");
		
		wait.until(ExpectedConditions.elementToBeClickable(logout_button));
		logout_button.click();
		System.out.println("Clicked on Logout.");

	}
	
}
	


