package technosapeince.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class StudentMenuBar extends AbstractComponents {

	WebDriver driver;

	public StudentMenuBar(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//

	@FindBy(css = ".css-1y1k3rb")
	WebElement studentmenu;

	@FindBy(xpath = "//span[normalize-space()='Enroled Courses']")
	WebElement enrolledbutton;

	@FindBy(css = "body")
	WebElement sideclick;

//-----------------------------------------------------------//

	public void openstudentmenu() {
		action(studentmenu);
	}

	public EnroledCourse sideclick() {

		sideclick.click();
		EnroledCourse enroledcourse = new EnroledCourse(driver);
		return enroledcourse;
	}

	public void enrolledCourse() throws InterruptedException {
		Thread.sleep(1000);
		enrolledbutton.click();

	}

	public void Dashboard() {
		gotoDashboard();
	}

}
