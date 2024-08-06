package technosapeince.pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import technosapeince.AbstractComponents.AbstractComponents;

public class CourseInformation extends AbstractComponents {

	WebDriver driver;

	public CourseInformation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	@FindBy(xpath = "//div[contains(@class,'css-w7fej9')]/button")
	WebElement enroll;

	@FindBy(xpath = "//button[text()='Click here to start learning']")
	WebElement Start;
	
	@FindBy(css=".SnackbarItem-message")
	WebElement loginmessage;

	By enrollbutton = By.xpath("//div[contains(@class,'css-w7fej9')]/button");
	By message = By.cssSelector("#notistack-snackbar");
	By startlearning = By.xpath("//button[text()='Click here to start learning']");

//-----------------------------------------------------------//

	public LoginPage enrollButton() {
		waitForElementToAppear(enrollbutton);
		enroll.click();
		LoginPage loginpage = new LoginPage(driver);
		return loginpage;

	}

	public LearnCourse startLearning() {

		waitForElementToAppear(message);
		loginmessage.getText();
		refreshpage();
		waitForElementToAppear(startlearning);
		Start.click();
		LearnCourse learncourse = new LearnCourse(driver);
		return learncourse;

	}
	
	public void loginmesssage() {
		
		loginmessage.getText();
	}

}
