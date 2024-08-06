package technosapeince.pageobjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	@FindBy(id = "email")
	WebElement studentemail;

	@FindBy(id="password")
	WebElement pwd;
	
	@FindBy(css="button[type='submit']")
	WebElement submit;
	
	@FindBy(css=".css-1m9pwf3")
	WebElement checkbox;
//--------------------------------------------------------------//
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message;//Invalid credentials
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message1;//Signin Success. Your free trial period has ended
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message2;//Both Email and Password are required
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message3;//Please enter a valid Email address
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message4;//The password field is required.
	
	@FindBy(css=".SnackbarItem-message")
	WebElement message5;//SignIn Success

	


//-----------------------------------------------------------//


	public void login(String email,String password) {
		studentemail.sendKeys(email);
		pwd.sendKeys(password);
		checkbox.click();
		submit.click();
	}
	
	public String geterrormessage() {
		waitWebElementToAppear(message);
		return message.getText();
	}
	
	public String successmessage() {
		waitWebElementToAppear(message1);
		return message1.getText();
	}
	
	public String geterrormessage1() {
		waitWebElementToAppear(message2);
		return message2.getText();
	}

	public String geterrormessage2() {
		waitWebElementToAppear(message3);
		return message3.getText();
	}
	
	public String geterrormessage3() {
		waitWebElementToAppear(message4);
		return message4.getText();
	}
	
	public String geterrormessage4() {
		waitWebElementToAppear(message5);
		return message5.getText();
	}
	
	
	public void checkBox() {
		
		checkbox.click();
	}
	

	
	
	
	
}
