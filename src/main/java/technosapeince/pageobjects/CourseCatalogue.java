package technosapeince.pageobjects;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import technosapeince.AbstractComponents.AbstractComponents;

public class CourseCatalogue extends AbstractComponents {

	WebDriver driver;

	public CourseCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	@FindBy(css = ".css-1sty9pu strong")
	List<WebElement> courseName;

	@FindBy(xpath = "//button[@title='Go to next page']")
	WebElement nextbutton;

//-----------------------------------------------------------//

	public List<WebElement> getCourseTitle() {
		
		return (List<WebElement>) courseName;
	}
	
	

	public WebElement getCategoryName(String courseName) {
		
		WebElement coursename = getCourseTitle().stream().filter(courses -> courses.getText().contains(courseName))
				.findFirst().orElse(null);
		
		return coursename;
		
		
	}
	
	public CourseInformation getCourseInformation() {
	    return new CourseInformation(driver);
	}
	
	public void nextbutton() {
		
		nextbutton.click();
	}
	

	
	public void findAndClickCourse(String courseName) {
	    boolean courseFound = false;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    while (!courseFound) {
	        List<WebElement> courses = getCourseTitle();

	        try {
	            // Check if the course name is present on the current page
	            WebElement coursename = getCategoryName(courseName);
	            if (coursename != null) {
	                // Ensure the element is visible and clickable
	                wait.until(ExpectedConditions.visibilityOf(coursename));
	                wait.until(ExpectedConditions.elementToBeClickable(coursename));

	                // Scroll to the element if necessary
	                js.executeScript("arguments[0].scrollIntoView(true);", coursename);
	                
	                // Click using JavaScript to avoid interception
	                js.executeScript("arguments[0].click();", coursename);
	                courseFound = true;
	            } else {
	                // If course name is not found, navigate to the next page
	                if (nextbutton.isDisplayed()) {
	                    nextbutton.click();
	                    // Wait for the page to load new courses
	                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".css-1sty9pu strong")));
	                } else {
	                    // If next button is not displayed, break the loop
	                    break;
	                }
	            }
	        } catch (NoSuchElementException | ElementClickInterceptedException e) {
	            // If no elements are found or click is intercepted, try to navigate to the next page
	            if (nextbutton.isDisplayed()) {
	                nextbutton.click();
	                // Wait for the page to load new courses
	                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".css-1sty9pu strong")));
	            } else {
	                // If next button is not displayed, break the loop
	                break;
	            }
	        }
	    }

	    if (!courseFound) {
	        System.out.println("Course not found: " + courseName);
	    }
	}
	
	

}
