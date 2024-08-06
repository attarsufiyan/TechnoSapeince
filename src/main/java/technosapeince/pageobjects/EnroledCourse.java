package technosapeince.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class EnroledCourse extends AbstractComponents {

	WebDriver driver;

	public EnroledCourse(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	
	@FindBy(css = ".css-1y1k3rb")
	WebElement studentmenu;
	
	@FindBy(css = ".css-zq6grw strong")
	List<WebElement> list;
	
	
	By enrolledlist=By.cssSelector(".css-zq6grw strong");
	
    
//-----------------------------------------------------------//
    
    
	public List<WebElement> enrolledlist() {
		
		waitForElementToAppear(enrolledlist);
		return (List<WebElement>) list;
		
	}
	
	public boolean checkcourse(String elist) {
		
		
		boolean match = enrolledlist().stream()
		        .anyMatch(enrolllists -> enrolllists.getText().equalsIgnoreCase(elist));
		 assertTrue(match, "Course '" + elist + "' is not enrolled successfully");
		 
		 if (match) {
			    System.out.println("Course is enrolled successfully");
			} else {
			    System.out.println("Course is not enrolled");
			}

		 return match;
		
	}
	
	
	
	
}
