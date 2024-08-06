package technosapeince;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaidCourseEnroll {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
        String courseName="AWS"
        		+ " | IT Infrastructure";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://technobug.in/");

		driver.findElement(By.xpath("//button[text()='Cancel']")).click();

		List<WebElement> category = driver.findElements(By.cssSelector(".css-9ajahe"));

		// css-1u9kyh5
//
		WebElement cat = category.stream().filter(categorys -> categorys.getText().equals("Information Technology"))
				.findFirst().orElse(null);
		// findElement(By.cssSelector("button")).
		cat.click();

		List<WebElement> subcategory = driver.findElements(By.cssSelector(".css-9ajahe"));

		WebElement subcat = subcategory.stream()
				.filter(subcategorys -> subcategorys.getText().equals("IT Infrastructure")).findFirst().orElse(null);

		subcat.click();
		
//		WebElement rowperpage=driver.findElement(By.xpath("//div[@id=':r6r:']"));
//		rowperpage.click();
//		driver.findElement(By.xpath("//ul[contains(@class,'css-r8u8y9')]/li[3]")).click();
//		
		//ul[contains(@class,'css-r8u8y9')]/li[3]

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		boolean courseFound = false;

		while (!courseFound) {
			js.executeScript("window.scrollTo(0, 0);");
			List<WebElement> course = driver.findElements(By.cssSelector(".css-1sty9pu strong"));
             
			WebElement coursename = course.stream().filter(
					courses -> courses.getText().contains(
			"AWS" 
			+ " | IT Infrastructure"))
					.findFirst().orElse(null);

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Go to next page']")));
			WebElement nextPageButton = driver.findElement(By.xpath("//button[@title='Go to next page']"));
			
			////div[contains(@class,'MuiTablePagination-actions')]/button[2]

			if (coursename == null) {
				nextPageButton.click();

			} else {
				
				coursename.click();
				break;
			}
		}
		
		
		//Click on Enroll Button		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'css-w7fej9')]/button")));
		WebElement enroll=driver.findElement(By.xpath("//div[contains(@class,'css-w7fej9')]/button"));
		enroll.click();
		
		//Enter the email address and password
		driver.findElement(By.id("email")).sendKeys("student@student.com");
		driver.findElement(By.id("password")).sendKeys("studentpassword");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		//id="notistack-snackbar"
		
		//Wait Until the message display after login
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#notistack-snackbar")));
		
		driver.navigate().refresh();
		//button[text()='Click here to start learning']
	     
         //Click on buy course button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Buy Course']")));
		WebElement buycoursebutton = driver.findElement(By.xpath("//button[text()='Buy Course']"));
		buycoursebutton.click();
		
		//CLick on pay with UPI
		driver.switchTo().frame(driver.findElement(By.cssSelector(".razorpay-checkout-frame")));
		driver.findElement(By.xpath("//*[@id=\"form-common\"]/div[1]/div[1]/div/div/div[2]/div/button[1]")).click();
		
		//Enter the UPI details
		driver.findElement(By.cssSelector(".vpa-title")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#vpa-upi")));
		driver.findElement(By.cssSelector("input#vpa-upi")).sendKeys("success@razarpay.com");
		driver.findElement(By.cssSelector("button#redesign-v15-cta")).click();
		
		
		
  	      
//		//Click on Volume Button
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".plyr__control--pressed")));
//	    driver.findElement(By.cssSelector(".plyr__control--pressed")).click();
//				
//		//Play the course 
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".plyr__control--overlaid")));
//		driver.findElement(By.cssSelector(".plyr__control--overlaid")).click();
		
		
	
	}
}


