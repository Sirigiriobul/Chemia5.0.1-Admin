package usersCreation;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.openqa.selenium.By;
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

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import net.bytebuddy.asm.Advice.Argument;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	Utililities util ;
	
	final int userName_colum=0;
	final static int displayName_column=6;
	final static int designation_column =7;
	final int department_column =10;
	final int role_name =11;
	final int addUser_status =15;
	final int final_status =16;
	final int login_final_status =17;
	final static int dbRef_column =13;

	
	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
//*******************************LeftMenu Icons****************************************
	@FindBy(xpath = "//div[@class='navbar-container']//a[@id='sidebar-toggle']")
	WebElement leftMenuIcon;
	@FindBy(xpath = "//a[@id='menuId']//span[text()='User Maintenance']")
	WebElement user_Maintenance;
	@FindBy(xpath = "//a[@id='subMenuId']//span[text()=' Manage user(s) ']")
	WebElement manage_users;
	
	@FindBy(xpath = "//a[@id='menuId']//span[text()='Department/Role']")
	WebElement department_Role;
	
	@FindBy(xpath = "//a[@id='subMenuId']//span[text()=' Dept Users ']")
	WebElement sub_department_users;
	//*******************************Departments/Users Icons****************************************

	@FindBy(xpath = "//p-dropdown[@id='selectDepartmentUserId']//div[@aria-label='dropdown trigger']/span")
	WebElement Department_Dropdown;
	
	@FindBy(xpath = "//input[@type='text' and contains(@class,'p-dropdown-filter')]")
	WebElement SearchBox;
	
	@FindBy(xpath = "//button[@id='addDeptUsersId']//span[contains(text(),'ADD')]")
	WebElement addDeptUsers_button;
	
	@FindBy(xpath = "//p-dropdown[@id='selectSubDeptId']//div[@aria-label='dropdown trigger']/span")
	WebElement Sub_Department_Dropdown;
	
	
	
	//*******************************AddUser Form Icons****************************************

	@FindBy(xpath = "//button[@id='addUserButtonsId']/span")
	WebElement add_button;
	
	@FindBy(xpath="//p-dialog[@id='addUserPopupId']//span[text()='Add User']")
	WebElement adduser_popup;
	
	@FindBy(xpath="//input[@id='userNamessId']")
	WebElement userID_input;
	@FindBy(xpath="//input[@id='employeeNumsId']")
	WebElement userEmpID_input;
	@FindBy(xpath="//p-dropdown[@id='titlessId']")
	WebElement title_drpdown;
	@FindBy(xpath="//p-dropdownitem//Li/span[text()='Select']")
	WebElement title_value;
	@FindBy(xpath="	//input[@id='firsstNameId']")
	WebElement firstName_input;
	@FindBy(xpath="//input[@id='middleNamesId']")
	WebElement middleName_input;
	@FindBy(xpath="//input[@id='lastNamesId']")
	WebElement lastName_input;
	@FindBy(xpath="//input[@id='displayNamesId']")
	WebElement displayName_input;
	@FindBy(xpath="//input[@id='designationsId']")
	WebElement designation_input;
	@FindBy(xpath="//input[@id='emailsId']")
	WebElement emailId_input;
	@FindBy(xpath="//input[@id='contactsNumId']")
	WebElement contactName_input;
	@FindBy(xpath="//button[@id='saveNewUsersId']//span[text()=' Save ']")
	WebElement save_button;
	@FindBy(xpath="//button[@id='savesCancelButtonsId']//span[text()=' Cancel ']")
	WebElement cancel_button;
	
	//*******************************AddUser to Department Form Icons****************************************
	@FindBy(xpath="//p-dropdown[@id='roleSelectionsId']//div[@aria-label='dropdown trigger']/span")
	WebElement roleSelections_dropdown;
	
	@FindBy(xpath="//p-dropdown[@id='siteSelectiosnId']//div[@aria-label='dropdown trigger']/span")
	WebElement site_dropdown;
	
	@FindBy(xpath="//p-dropdown[@id='referenceSelectiosnId']//div[@aria-label='dropdown trigger']/span")
	WebElement ref_dropdown;
	
	@FindBy(xpath="//app-search[@id='userSearchId']//input[@id='id']")
	WebElement userSearch_input;
	//th[text()=' Allow user to update settings/master data ']/..//td//input[@id='usrrr']
	
	@FindBy(xpath="//th[text()=' Allow user to update settings/master data ']/..//td//input[@id='usrrr']")
	WebElement metaData_checkBox;
	
	@FindBy(xpath="//button[@id='cancelSaveAddsId']//span[text()=' Cancel ']")
	WebElement addpt_cancel_button;
	
	@FindBy(xpath="//button[@id='saveAddsId']//span[text()=' Save ']")
	WebElement addpt_save_button;
	
	@FindBy(xpath="//p-toast//div[contains(@class,'p-toast-summary')]")
	WebElement toast_message;
	//**********************Verify User Creation*************************************
	@FindBy(xpath="//input[@id='searchDeptuserId']")
	WebElement searchDepUser_input;
	
	@FindBy(xpath="//tbody[@role='rowgroup']//tr//td[contains(@class,'userloginname')]")
	WebElement get_username;
	
	ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String readyState = (String) js.executeScript("return document.readyState");
			return readyState.equals("complete");
		}
	};
	public void clickOnAddInForm(WebElement addbutton ) {
		try {
			Thread.sleep(2000);
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollBy(0, document.body.scrollHeight);");
			wait.until(ExpectedConditions.elementToBeClickable(addbutton));
			
			action = new Actions(driver);
			action.moveToElement(addbutton).build().perform();
			addbutton.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.print("Unable to Click on Add Button");
		}
		
	}
	

	public void navigateToAdduserForm(ExtentTest test) throws Exception {
		System.out.println("method: navigateToAdduserForm ");

		ExtentTest parentNode1 = test.createNode("Navigating to Department users Page");

		wait.until(ExpectedConditions.elementToBeClickable(leftMenuIcon));
		leftMenuIcon.click();
		parentNode1.log(Status.PASS ,"Clicked on left menu");

		// wait.until(pageLoadCondition);
		wait.until(ExpectedConditions.elementToBeClickable(user_Maintenance));
		user_Maintenance.click();
		parentNode1.log(Status.PASS ,"Clicked on User Maintenance");

		wait.until(ExpectedConditions.elementToBeClickable(manage_users));
		manage_users.click();
		parentNode1.log(Status.PASS ,"Clicked on Mange Users");

		Thread.sleep(10000);
		//clickOnAddInForm(add_button);		

	}
	public void navigateToDapartmentrolesPage(ExtentTest test) throws Exception {
		System.out.println("method: navigateToDapartmentrolesPage ");

		ExtentTest parentNode1 = test.createNode("Navigating to Department users Page");

		wait.until(ExpectedConditions.elementToBeClickable(leftMenuIcon));
		leftMenuIcon.click();
		parentNode1.log(Status.PASS ,"Clicked on left menu");
		// wait.until(pageLoadCondition);
		wait.until(ExpectedConditions.elementToBeClickable(department_Role));
		department_Role.click();
		parentNode1.log(Status.PASS ,"Clicked on department Role");

		
		wait.until(ExpectedConditions.elementToBeClickable(sub_department_users));
		sub_department_users.click();
		parentNode1.log(Status.PASS ,"Clicked on Department users");

		Thread.sleep(10000);
		
	}
	
	public void clickDropDown(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	public void selectValue(String objectVal) {
		System.out.println("method: selectValue "+objectVal);

		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		SearchBox.click();
		SearchBox.sendKeys(objectVal);
		SearchBox.click();
		SearchBox.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		
	}//selectDepartment
	
	public void selectDepartment(String department) {
		System.out.println("method: selectDepartment ");

		wait.until(ExpectedConditions.elementToBeClickable(Department_Dropdown));
		Department_Dropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		SearchBox.click();
		SearchBox.sendKeys(department);
//		SearchBox.click();

		SearchBox.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		
	}//selectDepartment
	
	private void enterDetailsOfUserInDepartment(Map<String, Object> rowData) {

		System.out.println("method: enterDetailsOfAdduserInDepartments ");
		String dashBoardRef=(String) rowData.get("Dashboard Ref");
		String subDepart=(String) rowData.get("Select sub Department");
		String Department=(String) rowData.get("Select Department");
		System.out.println("dashBoardRef "+dashBoardRef);

		String displayName= (String) rowData.get("Display Name");
		if(!((String) rowData.get("Role Name")==null)) {
		clickDropDown(roleSelections_dropdown);
		selectValue((String) rowData.get("Role Name"));
		}
		
		if(!(rowData.get("Site")==null)) {
		clickDropDown(site_dropdown);
		selectValue((String) rowData.get("Site"));
		}
		if(!(dashBoardRef==null)) {
		clickDropDown(ref_dropdown);
		if(Department.equalsIgnoreCase("Quality Assurance")) {
		if(subDepart.equalsIgnoreCase("Generic DQA"))
			selectValue("QualityAssurance");
		else
			selectValue(dashBoardRef);

		}
		else
			selectValue(dashBoardRef);
		}
		
		userSearch_input.click();
		userSearch_input.sendKeys(displayName);
		
		WebElement checkBox=driver.findElement(By.xpath("//label[text()='"+displayName+"']//preceding-sibling::input"));
		checkBox.click();
		
		if(Department.equalsIgnoreCase("Quality Assurance")) {
		wait.until(ExpectedConditions.elementToBeClickable(metaData_checkBox));
		metaData_checkBox.click();
		}
	
	}//enterDetailsOfUserInDepartment

	public void enterDetailsOfUserInDepartment(String designation,String sitename, String dbRef,String displayName) {
		System.out.println("method: enterDetailsOfAdduserInDepartments ");

		clickDropDown(roleSelections_dropdown);
		selectValue(designation);
		
		clickDropDown(site_dropdown);
		selectValue(sitename);

		clickDropDown(ref_dropdown);
		if(dbRef.equalsIgnoreCase("Generic DQA"))
			selectValue("QualityAssurance");
		else
			selectValue(dbRef);
		
		userSearch_input.click();
		userSearch_input.sendKeys(displayName);
		
		WebElement checkBox=driver.findElement(By.xpath("//label[text()='"+displayName+"']//preceding-sibling::input)"));
		checkBox.click();
		
		
		
	}//enterDetailsOfAdduserInDepartments
	
	public void addUserToDepartment(int sheetNumber,int i,String sitename,ExtentTest test) throws IOException {
		System.out.println("method: addUsersToDepartments ");
		util = new Utililities();

		ExtentTest parentNode1 = test.createNode("Adding Users to Respective Departments");

		 String userName= util.getDataFromXl(sheetNumber, i,userName_colum).trim();
		 String Department =util.getDataFromXl(sheetNumber, i,department_column).trim();
		 String displayName= util.getDataFromXl(sheetNumber, i,displayName_column).trim();
		 String status = util.getDataFromXl(sheetNumber, i,addUser_status).trim();
		 String roleName= util.getDataFromXl(sheetNumber, i,role_name).trim();
		 String dbref= util.getDataFromXl(sheetNumber, i,dbRef_column).trim();

		 System.out.println("Department: "+Department);
		 System.out.println("Add user status: "+status);
		 
			parentNode1.log(Status.PASS ,"Add user status: "+status);

		 if(status.equalsIgnoreCase("Pass")) {
			 
//			 String [] arr1=userName.split("-");
//			 System.out.print("username array: "+Arrays.toString(arr1));
//			 
			 if(!Department.equalsIgnoreCase("Quality Assurance")) {
				 if(Department.equalsIgnoreCase("Chemistry Research and Developments."))
				 {
					clickDropDown(Department_Dropdown);
					selectValue("Chemistry Research and Developments.");
					parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Chemistry Research and Developments");

				 }
				 else if(Department.equalsIgnoreCase("Formulation Research and Development."))
				 {
					clickDropDown(Department_Dropdown);
					selectValue("Formulation Research and Development.");
					parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Formulation Research and Development.");

				 }		
				 else if(Department.equalsIgnoreCase("Analytical Research and Development"))
				 {
					clickDropDown(Department_Dropdown);
					selectValue("Analytical Research and Development");
					parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Analytical Research and Development");

				}	
				clickOnAddInForm(addDeptUsers_button);
				parentNode1.log(Status.PASS ,"Clicked on add button in department users form ");
				try {
				enterDetailsOfUserInDepartment(roleName,sitename,dbref,displayName);
				parentNode1.log(Status.PASS ,"Details entered in department Add users form for "+userName);
				}catch(Exception e) {
					System.out.println("Exception occured while entering add Department User details: "+e);
				}
			 }
			 else
			 {
				clickDropDown(Department_Dropdown);
				selectValue("Quality Assurance");
				parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Quality Assurance");

				clickDropDown(Sub_Department_Dropdown);
				selectValue(roleName);
				parentNode1.log(Status.PASS ,"Selected value from departments dropdown-->"+roleName);

				clickOnAddInForm(addDeptUsers_button);
				try{
					enterDetailsOfUserInDepartment(roleName,sitename,dbref,displayName);
				parentNode1.log(Status.PASS ,"Details entered in department Add users form for QA "+userName);}
				catch(Exception e) {
					System.out.println("Exception occured while entering add Department User details: "+e);
				}
			 }		 
		 }//if
		 else
			 System.out.println("Add user status: "+status+"Unable to add user to department");
		 
	}//addUsersToDepartments
	
	public void refreshpage() {
		System.out.println("method: refreshpage ");

		driver.navigate().refresh();

	}
	public void clickOnsaveInAddUserToDep() {
		System.out.println("method: clickOnsaveInAddUserToDep ");

		clickOnAddInForm(addpt_save_button);
	}
	
	public void clickOnCancelInAddUserToDep() {
		System.out.println("method: clickOnCancelInAddUserToDep ");

		clickOnAddInForm(addpt_cancel_button);
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	public void selectTitle(String val) {
		
		WebElement ele=driver.findElement(By.xpath("//p-dropdownitem//Li/span[text()='"+val+"']"));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();

	}
	public Object getUserName(int i,int sheetNumber) throws IOException {
		System.out.println("method: getUserName ");
		util = new Utililities();
		 ArrayList<Object> rowData=util.geRrowData(i, sheetNumber);

		return rowData.get(0);	
	}
	public void enterDetailsInAddUserForm(int i,int sheetNumber,ExtentTest test) throws IOException, InterruptedException {
		
		System.out.println("method: enterDetailsInAddUserForm ");
		util = new Utililities();
		ExtentTest parentNode1 = test.createNode("To create Users Entering Details  in Add Users from");

			 
			 ArrayList<Object> rowData=util.geRrowData(i, sheetNumber);
			 
					wait.until(ExpectedConditions.elementToBeClickable(userID_input));
					userID_input.clear();
					userID_input.sendKeys(rowData.get(0).toString());
					parentNode1.log(Status.PASS ,"Eneterd UserName as:"+rowData.get(0));

					this.scrollToElement(userEmpID_input);
					userEmpID_input.clear();
					userEmpID_input.sendKeys(rowData.get(1).toString());
					parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+rowData.get(1));

					this.scrollToElement(title_drpdown);
					title_drpdown.click();
					selectTitle(rowData.get(2).toString());
					parentNode1.log(Status.PASS ,"Eneterd Title value as:"+rowData.get(2));

					this.scrollToElement(firstName_input);
					firstName_input.clear();
					firstName_input.sendKeys(rowData.get(3).toString());
					parentNode1.log(Status.PASS ,"Eneterd First Name value as:"+rowData.get(3));

					
					this.scrollToElement(middleName_input);
					middleName_input.clear();
					middleName_input.sendKeys(rowData.get(4).toString());
					parentNode1.log(Status.PASS ,"Eneterd Middle Name value as:"+rowData.get(4));

					this.scrollToElement(lastName_input);
					lastName_input.clear();
					lastName_input.sendKeys(rowData.get(5).toString());
					parentNode1.log(Status.PASS ,"Eneterd Last Name value as:"+rowData.get(5));

					this.scrollToElement(displayName_input);
					displayName_input.clear();
					displayName_input.sendKeys(rowData.get(6).toString());
					parentNode1.log(Status.PASS ,"Eneterd Display Name value as:"+rowData.get(6));

					
					this.scrollToElement(designation_input);
					designation_input.clear();
					designation_input.sendKeys(rowData.get(7).toString());
					parentNode1.log(Status.PASS ,"Eneterd Designation value as:"+rowData.get(7));

					this.scrollToElement(emailId_input);
					emailId_input.clear();
					//emailId_input.sendKeys(rowData.get(8).toString());
					parentNode1.log(Status.PASS ,"Eneterd Email Address value as:"+rowData.get(8));

					
					this.scrollToElement(contactName_input);
					contactName_input.clear();
					//contactName_input.sendKeys(rowData.get(9).toString());
					parentNode1.log(Status.PASS ,"Eneterd Contact Name value as:"+rowData.get(9));

					Thread.sleep(3000);
			 
	}
	public void clickOnCancelInCreateUser() {
		System.out.println("method: clickOnCancelInCreateUser ");

		clickOnAddInForm(cancel_button);
	}
	public void clickOnsaveInCreateUser() {
		System.out.println("method: clickOnsaveInCreateUser ");

		clickOnAddInForm(save_button);
	}
	
	public void clickOnAddInManageUser() {
		System.out.println("method: clickOnAddInManageUser ");

		clickOnAddInForm(add_button);
	}
	public String isScuccessfulMessageDIsplayed() {
		System.out.println("method: clickOnAddInManageUser ");
		
		wait.until(ExpectedConditions.elementToBeClickable(toast_message));
		return toast_message.getText();
		
	}


	public void clickOnSearchUser(String userName) {
		System.out.println("method: clickOnSearchUser ");
		
		wait.until(ExpectedConditions.elementToBeClickable(searchDepUser_input));
		searchDepUser_input.clear();
		searchDepUser_input.sendKeys(userName);
	}
	public String getUserName() {
		System.out.println("method: getUserName");
		wait.until(ExpectedConditions.elementToBeClickable(get_username));

		return get_username.getText();
		
	}//getUserName
	
	public void navigateToRelatedDepartment(String Department,String subDepartment,ExtentTest test) {
		System.out.println("method: navigateToRelatedDepartment ");

		ExtentTest parentNode1 = test.createNode("Navigate To RelatedDepartment");

//		 String [] arr1=userName.split("-");
//		 System.out.print("username array: "+Arrays.toString(arr1));
//		 
		 if(!Department.equalsIgnoreCase("Quality Assurance")) {
			 if(Department.equalsIgnoreCase("Chemistry Research and Developments."))
			 {
				clickDropDown(Department_Dropdown);
				selectValue("Chemistry Research and Developments.");
				parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Chemistry Research and Developments");

			 }
			 else if(Department.equalsIgnoreCase("Formulation Research and Development."))
			 {
				clickDropDown(Department_Dropdown);
				selectValue("Formulation Research and Development.");
				parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Formulation Research and Development.");

			 }		
			 else if(Department.equalsIgnoreCase("Analytical Research and Development"))
			 {
				clickDropDown(Department_Dropdown);
				selectValue("Analytical Research and Development");
				parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Analytical Research and Development");

			}	
						
		 }
		 else
		 {
			clickDropDown(Department_Dropdown);
			selectValue(Department);
			parentNode1.log(Status.PASS ,"Selected value from departments dropdown--> Quality Assurance");

			clickDropDown(Sub_Department_Dropdown);
			selectValue(subDepartment);
			
			parentNode1.log(Status.PASS ,"Selected value from SUb departments dropdown."+subDepartment);
		 }		 
	 
	}//selectDepartment


	public void addUserToDepartment1(Map<String, Object> rowData, ExtentTest test) {

		System.out.println("method: addUsersToDepartment1 ");
		util = new Utililities();

		ExtentTest parentNode1 = test.createNode("Adding Users to Respective Departments");
		 String Department = (String) rowData.get("Select Department");
		 String subDepartment = (String) rowData.get("Select sub Department");

		 String userName = (String) rowData.get("User Name");
		System.out.println("userName : "+userName);
//		 System.out.println("Add user status: "+status);
//		 
//			parentNode1.log(Status.PASS ,"Add user status: "+status);

			 
			navigateToRelatedDepartment(Department,subDepartment,test);
			clickOnAddInForm(addDeptUsers_button);
			//try {
				enterDetailsOfUserInDepartment(rowData);
				parentNode1.log(Status.PASS ,"Details entered in department Add users form for "+userName);
				//}catch(Exception e) {
				//	System.out.println("Exception occured while entering add Department User details: "+e);
				//}
			 	
		
		 
	
		
	}//addUserToDepartment1


	public void enterDetailsInAddUserForm1(Map<String, Object> rowData, ExtentTest test) throws InterruptedException {
		System.out.println("method: enterDetailsInAddUserForm1 ");
		
		util = new Utililities();
		ExtentTest parentNode1 = test.createNode("To create Users Entering Details  in Add Users from");
		
		String userName = (String) rowData.get("User Name");
					if(!(userName==null)) {
						wait.until(ExpectedConditions.elementToBeClickable(userID_input));
						userID_input.clear();
						userID_input.sendKeys(userName);
						parentNode1.log(Status.PASS ,"Eneterd UserName as:"+userName);
					}
					
					if(!((String) rowData.get("Employee Number")==null)) {
						this.scrollToElement(userEmpID_input);
						userEmpID_input.clear();
						userEmpID_input.sendKeys((String) rowData.get("Employee Number"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Employee Number"));
					}
					
					if(!((String) rowData.get("Title")==null)) {
						this.scrollToElement(title_drpdown);
						title_drpdown.click();
						selectTitle((String) rowData.get("Title"));
						parentNode1.log(Status.PASS ,"Eneterd Title value as:"+(String) rowData.get("Title"));
					}
					
					if(!((String) rowData.get("First Name")==null)) {
						this.scrollToElement(firstName_input);
						firstName_input.clear();
						firstName_input.sendKeys((String) rowData.get("First Name"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("First Name"));
					}
					
					if(!((String) rowData.get("Middle Initials")==null)) {
						this.scrollToElement(middleName_input);
						middleName_input.clear();
						middleName_input.sendKeys((String) rowData.get("Middle Initials"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Middle Initials"));
					}
					if(!((String) rowData.get("Last Name")==null)) {
						this.scrollToElement(lastName_input);
						lastName_input.clear();
						lastName_input.sendKeys((String) rowData.get("Last Name"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Last Name"));
					}
					
					if(!((String) rowData.get("Display Name")==null)) {
						this.scrollToElement(displayName_input);
						displayName_input.clear();
						displayName_input.sendKeys((String) rowData.get("Display Name"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Display Name"));
					}
					if(!((String) rowData.get("Designation")==null)) {
						this.scrollToElement(designation_input);
						designation_input.clear();
						designation_input.sendKeys((String) rowData.get("Designation"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Designation"));
					}
					
					if(!((String) rowData.get("Email Address")==null)) {
						this.scrollToElement(emailId_input);
						emailId_input.clear();
						emailId_input.sendKeys((String) rowData.get("Email Address"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Email Address"));
					}
					
					if(!((String) rowData.get("Contact Number")==null)) {
						this.scrollToElement(contactName_input);
						contactName_input.clear();
						contactName_input.sendKeys((String) rowData.get("Contact Number"));
						parentNode1.log(Status.PASS ,"Eneterd Employee Number value as:"+(String) rowData.get("Contact Number"));
					}

					Thread.sleep(3000);
			 
	
	}


	
}
