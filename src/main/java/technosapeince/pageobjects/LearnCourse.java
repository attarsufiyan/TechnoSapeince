package technosapeince.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class LearnCourse extends AbstractComponents {

	WebDriver driver;

	public LearnCourse(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
//-------------------------------------------------------------//
	@FindBy(css = ".plyr__control--pressed")
	WebElement volume;
	
	@FindBy(css=".plyr__control--overlaid")
	WebElement play;
	
	@FindBy(css = ".css-1y1k3rb")
	WebElement studentmenu;

	By volumebutton = By.cssSelector(".plyr__control--pressed");
	
	By playbutton = By.cssSelector(".plyr__control--overlaid");

	
//-----------------------------------------------------------//

	
	public void volumeButton() {
		waitForElementToAppear(volumebutton);
		volume.click();	
	}
	
	public StudentMenuBar playButton() {
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 jsExecutor.executeScript("window.scrollBy(0, -500);");
		waitForElementToAppear(playbutton);
		play.click();
		StudentMenuBar studentmenubar = new StudentMenuBar(driver);
		return studentmenubar;
	}
	
}
