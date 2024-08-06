package technosapeince;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TrueCopy {

	public static void main(String[] args) throws InterruptedException {
		
		String Category="Information Technology";
		String SubCategory="IT Infrastructure";
		String courseName = "Datacenter Architect Program" 
		                     + " | IT Infrastructure";
		String elist = "Datacenter Architect Program";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://technobug.in/");

		driver.findElement(By.xpath("//button[text()='Cancel']")).click();

		List<WebElement> category = driver.findElements(By.cssSelector(".css-9ajahe"));

		// css-1u9kyh5
//
		WebElement cat = category.stream().filter(categorys -> categorys.getText().equals(Category))
				.findFirst().orElse(null);
		// findElement(By.cssSelector("button")).
		cat.click();

		List<WebElement> subcategory = driver.findElements(By.cssSelector(".css-9ajahe"));

		WebElement subcat = subcategory.stream()
				.filter(subcategorys -> subcategorys.getText().equals(SubCategory)).findFirst().orElse(null);

		subcat.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		boolean courseFound = false;

		while (!courseFound) {
			js.executeScript("window.scrollTo(0, 0);");
			List<WebElement> course = driver.findElements(By.cssSelector(".css-1sty9pu strong"));

			WebElement coursename = course.stream().filter(courses -> courses.getText().contains(courseName))
					.findFirst().orElse(null);

			WebElement nextPageButton = driver.findElement(By.xpath("//button[@title='Go to next page']"));


			if (coursename == null) {
				nextPageButton.click();

			} else {

				coursename.click();
				break;
			}
		}

		// Click on Enroll Button
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'css-w7fej9')]/button")));
		WebElement enroll = driver.findElement(By.xpath("//div[contains(@class,'css-w7fej9')]/button"));
		enroll.click();

		// Enter the email address and password
		driver.findElement(By.id("email")).sendKeys("student@student.com");
		driver.findElement(By.id("password")).sendKeys("studentpassword");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		// id="notistack-snackbar"

		// Wait Until the message display after login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#notistack-snackbar")));

		driver.navigate().refresh();
		// button[text()='Click here to start learning']

		// Click here to start learning button
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[text()='Click here to start learning']")));
		WebElement startlearningbutton = driver
				.findElement(By.xpath("//button[text()='Click here to start learning']"));
		startlearningbutton.click();

		// Click on Volume Button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".plyr__control--pressed")));
		driver.findElement(By.cssSelector(".plyr__control--pressed")).click();

		// Play the course
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".plyr__control--overlaid")));
		driver.findElement(By.cssSelector(".plyr__control--overlaid")).click();

		// Check the course is Enrolled or not
		Actions a = new Actions(driver);
		WebElement move = driver.findElement(By.cssSelector(".css-1y1k3rb"));
		a.moveToElement(driver.findElement(By.cssSelector(".css-1y1k3rb"))).build().perform();

		// Click on Enrolled Courses
		driver.findElement(By.xpath("(//span[text()='Enroled Courses '])[1]")).click();
		a.moveToElement(driver.findElement(By.cssSelector("body"))).click().perform();

		// Fetch all list of enrolled course
		js.executeScript("window.scrollTo(0, 500);");
		js.executeScript("window.scrollTo(0, -2000);");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-zq6grw strong")));
		List<WebElement> enrolllist = driver.findElements(By.cssSelector(".css-zq6grw strong"));
		boolean match = enrolllist.stream()
		        .anyMatch(enrolllists -> enrolllists.getText().equalsIgnoreCase(elist));
		   assertTrue(match, "Course '" + elist + "' is not enrolled successfully");

		// Check the result of the match
		if (match) {
		    System.out.println("Course is enrolled successfully");
		} else {
		    System.out.println("Course is not enrolled");
		}

	}
}
