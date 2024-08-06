package technosapeince;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import technosapeince.pageobjects.CourseCatalogue;
import technosapeince.pageobjects.CourseInformation;
import technosapeince.pageobjects.EnroledCourse;
import technosapeince.pageobjects.Homepage;
import technosapeince.pageobjects.LearnCourse;
import technosapeince.pageobjects.LoginPage;
import technosapeince.pageobjects.StudentMenuBar;

public class TrueCopy1 {

	public static void main(String[] args) throws InterruptedException {
		
		String Category="Information Technology";
		String SubCategory="IT Infrastructure";
		String courseName = "IT Support and Networking Essentials" 
		                     + " | IT Infrastructure";
		String elist = "IT Support and Networking Essentials";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
        Homepage homepage=new Homepage(driver);
        homepage.goTo();
        homepage.clickCancelButton();
        List<WebElement>category=homepage.getCategory();
        homepage.getCategoryName(Category);
        homepage.catClick(Category);
        List<WebElement>subcategory=homepage.getSubCategory();
        homepage.getSubCategory();
        homepage.subClick(SubCategory);
     
       
       
       
		boolean courseFound = false;

		while (!courseFound) {
			
			CourseCatalogue coursecatalogue = new CourseCatalogue(driver);
			List<WebElement>name=coursecatalogue.getCourseTitle();
			coursecatalogue.nextbutton();

			if ( name== null) {
				coursecatalogue.nextbutton();

			} else {
				WebElement coursename = coursecatalogue.getCategoryName(courseName);
				coursename.click();
				courseFound = true;
				break;
			}
		}

		// Click on Enroll Button
		CourseInformation courseinformation=new CourseInformation(driver);
		courseinformation.enrollButton();
		
        //Enter the login details 
		LoginPage loginpage = new LoginPage(driver);
        loginpage.login("student@student.com", "studentpassword");
        courseinformation.startLearning();

        LearnCourse learncourse = new LearnCourse(driver);
        learncourse.volumeButton();
        learncourse.playButton();
  
		StudentMenuBar studentmenubar = new StudentMenuBar(driver);
		studentmenubar.gotostudentmneu();
		studentmenubar.enrolledCourse();
		studentmenubar.sideclick();
		
		EnroledCourse enroledcourse = new EnroledCourse(driver);
		List<WebElement>enroll=enroledcourse.enrolledlist();
		boolean match=enroledcourse.checkcourse(elist);

	}
}
