package technosapeince;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import technosapeince.TestComponents.BaseTest;
import technosapeince.pageobjects.CourseCatalogue;
import technosapeince.pageobjects.CourseInformation;
import technosapeince.pageobjects.EnroledCourse;
import technosapeince.pageobjects.LearnCourse;
import technosapeince.pageobjects.LoginPage;
import technosapeince.pageobjects.StudentMenuBar;
import technosapeince.pageobjects.describeyoubest;

public class FreeCourseEnroll extends BaseTest {

//	String Category = "Information Technology";
//	String SubCategory = "IT Infrastructure";
//	String courseName = "IT Support and Networking Essentials" + " | IT Infrastructure";
//	String elist = "IT Support and Networking Essentials";

	@Test(dataProvider = "getData")
	public void courseEnroll(HashMap<String, String> input) throws Exception {

		homepage.clickCancelButton();
		List<WebElement> category = homepage.getCategory();
		homepage.getCategoryName(input.get("Category"));
		homepage.catClick(input.get("Category"));
		List<WebElement> subcategory = homepage.getSubCategory();
		homepage.getSubCategory();
		CourseCatalogue coursecatalogue = homepage.subClick(input.get("SubCategory"));

		coursecatalogue.findAndClickCourse(input.get("courseName"));

		// Click on Enroll Button
		CourseInformation courseinformation = coursecatalogue.getCourseInformation();
		LoginPage loginpage = courseinformation.enrollButton();

		// Enter the login details
		loginpage.login(input.get("email"), input.get("pwd"));
		LearnCourse learncourse = courseinformation.startLearning();

		learncourse.volumeButton();
		StudentMenuBar studentmenubar = learncourse.playButton();

		studentmenubar.gotostudentmneu();
		studentmenubar.enrolledCourse();
		EnroledCourse enroledcourse = studentmenubar.sideclick();

		List<WebElement> enroll = enroledcourse.enrolledlist();
		boolean match = enroledcourse.checkcourse(input.get("elist"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\technosapeince\\data\\CourseData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) },{ data.get(2) } };

	}

	@Test(groups = { "ErrorValidation" })
	public void checkenrolledCourse() throws InterruptedException {

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asStudent();
		loginpage.login("student@student.com", "studentpassword");
		// homepage.goDashboard();
		Thread.sleep(2000);

	}

	@Test(groups = { "ErrorValidation" })
	public void searchbardisplay() throws Exception {
		// Verify the search is enabled and displayed or not.
		Assert.assertTrue(homepage.searchBarDisplayed());
		Assert.assertTrue(homepage.searchBarEnabled());

	}

	@Test(groups = { "ErrorValidation" })
	public void searchbar() throws Exception {
		// In searchbar it is accepting the space in input and showing the result
		Assert.assertTrue(homepage.searchbartc_1(), "It is accepting spacebar");
	}

	public void educatorprofile() {

	}

}
