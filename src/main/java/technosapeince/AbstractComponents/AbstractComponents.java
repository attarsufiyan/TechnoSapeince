package technosapeince.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import technosapeince.pageobjects.Dashboard;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//---------------------------------------------------------------------------------//
	@FindBy(css = ".css-1y1k3rb")
	WebElement studentmenu;
	
	@FindBy(xpath = "//span[normalize-space()='Enroled Courses']")
	WebElement enrolledbutton;
	
	@FindBy(xpath = "//span[normalize-space()='Dashboard']")
	WebElement dashboardbutton;
	
	@FindBy(css = ".css-1oxdjg7")
	WebElement searchbar;
	
	@FindBy(css = ".css-qflytl")
	WebElement searchbarinput;
	
	//.css-qflytl
	
	//span[normalize-space()='Dashboard']
	
	//----------------------------------------------------------------------------------//
	
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForallElementlocatedby(By findBy) {

		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void refreshpage() {
		driver.navigate().refresh();
	}
	

    public void action(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    
    public Dashboard gotostudentmneu() {
    
    	action(studentmenu);
    	Dashboard dashboard=new Dashboard(driver);
    	return dashboard;
    }

	//Enrolled Course
    public void enrolledCourse() throws InterruptedException {
    	Thread.sleep(1000);
		enrolledbutton.click();

	}
    
    public Dashboard gotoDashboard() {
    	//gotostudentmneu();
    	dashboardbutton.click();
    	Dashboard dashboard=new Dashboard(driver);
    	return dashboard;
    	
    }
    
    public WebElement searchBar() {
    	return searchbar;
    	
    }
    
    public WebElement searchBarinput() {
    	return searchbarinput;
    	
    }
    
    

}
