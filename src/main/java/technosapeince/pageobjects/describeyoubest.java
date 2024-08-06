package technosapeince.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class describeyoubest extends AbstractComponents {

	WebDriver driver;

	public describeyoubest(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	
	@FindBy(xpath = "//div[contains(@class,'css-170ukis')][1]")
	WebElement student;
	
	@FindBy(xpath = "//div[contains(@class,'css-170ukis')][2]")
	WebElement educator;
	
	@FindBy(xpath = "//div[contains(@class,'css-170ukis')][3]")
	WebElement institute;
    
//-----------------------------------------------------------//
	
	public LoginPage asStudent() {
		student.click();
		LoginPage loginpage=new LoginPage(driver);
		return loginpage;
	}

	public LoginPage asEducator() {
		educator.click();
		LoginPage loginpage=new LoginPage(driver);
		return loginpage;
	}
	public LoginPage asInstitute() {
		institute.click();
		LoginPage loginpage=new LoginPage(driver);
		return loginpage;
	}
	
	
	
	
	
}
