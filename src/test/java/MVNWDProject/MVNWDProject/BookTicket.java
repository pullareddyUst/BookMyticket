package MVNWDProject.MVNWDProject;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.apache.log4j.Logger;

public class BookTicket 
{
    //WebDriver instance
	//public static WebDriver driver;
	public static RemoteWebDriver driver;
	public static final Logger logger = Logger.getLogger(BookTicket.class);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	
	//set up method
	@BeforeClass
	public static void setUp() throws Exception{
		
		System.out.println("Starting setUp");
		logger.info("Starting setUp");		
        //ChromeDriver settings
		System.setProperty("webdriver.chrome.driver", "C:\\Driverswd\\chromedriver_win32\\chromedriver.exe");
        		
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
        //capabilities.setCapability(ChromeOptions.CAPABILITY,options);
		
		driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
        Thread.sleep(5000);
        //driver.manage().deleteAllCookies();		
		
	}	
	
	//Test methods
    @Test
    public void bookTicket()
    {            
    	
    	navigateToURL();
    	//signUp();
    	
    	System.out.println("Before Sign in@test");
    	
    	signIn();
    	System.out.println("After Sign in@test");
    	System.out.println("Before bookHotel@test");
    	bookHotel();
    	System.out.println("After Book hotel@test");
    	System.out.println("Before log out@test");
		logOut();		
		System.out.println("After log out@test");		
		
    }
    
    @AfterClass
    public static void tearDown() {    	
    	driver.quit();
		System.out.println("End of Test_BookTicket");    	
    }
    
    public void navigateToURL() {
    	
    	 //Navigate to url
    	logger.info("Navigate to url");
		driver.navigate().to("https://www.makemytrip.com");
		
		globalWaitSeconds(20);
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		pageLoadTimeoutSeconds(10);
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        
        //verify url title 
        logger.info("Verifying the Title of url");
		String appTitle = driver.getTitle();
		Boolean titleHasText = appTitle.contains("MakeMyTrip");
		System.out.println("Application title is :: "+appTitle);
		assertTrue(titleHasText);
    }
    
    public void signUp() {
    	
    	//imgDismiss();
    	//Sign up     
    	globalWaitSeconds(10);
    	driver.findElementByXPath("//p[contains(text(),'Login or Create Account')]").click();
    	driver.switchTo().alert();
    	//driver.findElementById("username").click();
    	driver.findElementById("username").sendKeys("9620800954");
    	driver.findElementByXPath("//button/span[contains(text(),'Continue')]").click();
    	//need to pass OTP here to test
    	driver.findElementByName("verifyOTPText").sendKeys("");
    	driver.findElementByXPath("//button/span[contains(text(),'Verify & Create Account')]").click();
    	
    }
    
    public void signIn() {
    	
    	
    	globalWaitSeconds(10);
    	System.out.println("inside signin");    	
    	
    	System.out.println("before sign in button click");
		
    	driver.findElementByXPath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser']").click();
		System.out.println("after sign in click");

		jse.executeScript("window.focus();");
		globalWaitSeconds(20);
		jse.executeScript("window.focus();");
		
		//element scroll into view
		WebElement userName = driver.findElementByXPath("//input[@id='username']");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", userName);
				
		driver.findElementByXPath("//input[@id='username']").clear();
    	driver.findElementByXPath("//input[@id='username']").sendKeys("bsrinivasa0206@gmail.com");
    	
    	jse.executeScript("window.focus();");
    	WebElement continueUN = driver.findElementByXPath("//span[contains(text(),'Continue')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueUN);
    	jse.executeScript("window.focus();");    			
    	driver.findElementByXPath("//span[contains(text(),'Continue')]").click();
    	
    	jse.executeScript("window.focus();");
    	globalWaitSeconds(10);
    	WebElement passWord = driver.findElementByXPath("//input[@id='password']");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passWord);
		// close button of image-notification bar ->//a[@id='webklipper-publisher-widget-container-notification-close-div']
    	jse.executeScript("window.focus();");
    	driver.findElementByXPath("//input[@id='password']").clear();
    	jse.executeScript("window.focus();");
    	driver.findElementByXPath("//input[@id='password']").sendKeys("deebaguntla9$");
    	jse.executeScript("window.focus();");
    	
    	globalWaitSeconds(10);
    	WebElement login = driver.findElementByXPath("//div[@class='btnContainer appendBottom25 ']//span[contains(text(),'Login')]");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
    	
    	driver.findElementByXPath("//div[@class='btnContainer appendBottom25 ']//span[contains(text(),'Login')]").click();
    	jse.executeScript("window.focus();");
    	assertTrue(driver.findElementByXPath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser userLoggedIn']//p[contains(text(),'Hey')]").isDisplayed());
					
    }

    
    public void bookHotel() {
    	
    	
    	globalWaitSeconds(10);
    	driver.findElementByXPath("//span[@class='chNavIcon appendBottom2 chSprite chHotels ' ]").click();
    	
    	driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").click();
    	driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
    	
    	//select check in date
    	driver.findElementByXPath("//input[@id='checkin']").click();
    	driver.findElementByXPath("//div[contains(text(),'28')]").click();
    	
    	//select checkout date
    	driver.findElementByXPath("//input[@id='checkout']").click();
    	driver.findElementByXPath("//div[contains(text(),'11')]").click();
    	
    	//click on search button
    	driver.findElementByXPath("//button[@id='hsw_search_button']").click();
    	
    	//select the first displayed option    	
    	driver.findElementByXPath("//div[@class='priceDetailsTop']").click();
    	
    	//click on book this now button
    	driver.findElementByXPath("//a[contains(text(),'BOOK THIS NOW')]").click();
    	
    	//click on paynow button    	
    	driver.findElementByXPath("//a[@class='primaryBtn btnPayNow']").click();
    	
    	//enter user details
    	driver.findElementById("fName").sendKeys("FirstName1");
    	driver.findElementById("lName").sendKeys("LaststName1");
    	driver.findElementById("lName").sendKeys("LaststName1");
    	driver.findElementByXPath("//div[contains(text(),'Pay Now')]").click();
    	
    	//pay using credit/debit card
    	driver.findElementByXPath("//span[contains(text(),'Credit/Debit Cards')]").click();
    	driver.findElementById("//input[@id='PAYMENT_cardNumber']").sendKeys("4444 4444 4444 4444");
    	driver.findElementByXPath("//span[contains(text(),'Make Payment ')]").click();
    	String invalidCardMsg = driver.findElementByXPath("//span[contains(text(),'Enter a valid card number')]").getText();
    	Boolean invalidCardText = invalidCardMsg.contains("Enter a valid card number");
    	assertTrue(invalidCardText);  	    	    	
    	
    }
    
    public void imgDismiss() {
    	
    	System.out.println("Inside image dismiss");
    	
    	//if (driver.findElementByXPath("//div[@id='slider']").isDisplayed()) {
    	//if (driver.findElementByXPath("//destination_publishing_iframe_makemytrip_0").isDisplayed()) {
    	
    	if (driver.findElementByXPath("//div[@id='slider']").isDisplayed()) {
    		System.out.println("After slider identified/before close slider");
    		//driver.switchTo().frame(frameElement)
    		
    		//driver.findElementByXPath("//a[@id='webklipper-publisher-widget-container-notification-close-div']").click();
    		doDoubleClick();
    		System.out.println("After image dismiss");
    	}
    }
    public void logOut() {
    	
    	//imgDismiss();
    	//logout of URL
    	System.out.println("Inside logout");
    	driver.findElementByXPath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser userLoggedIn']").click();    	
    	//driver.findElementByXPath("//p[contains(text(),'Logout')]").click();
    	
    	driver.findElementByXPath("//a[@class='userItems makeFlex appendBottom10']").click();
    	assertTrue(driver.findElementByXPath(" //p[contains(text(),'Login or Create Account')]").isDisplayed());
    	
    }
    
    public void globalWaitSeconds(int x) {
    	driver.manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
    }
    
    public void pageLoadTimeoutSeconds(int y) {
    	
    	driver.manage().timeouts().pageLoadTimeout(y, TimeUnit.SECONDS);
    	
    }
    
    public void doDoubleClick(){
        WebElement body = driver.findElement(By.tagName("body"));
        Actions actions= new Actions(driver).doubleClick(body);
        actions.build().perform();
    }
}
