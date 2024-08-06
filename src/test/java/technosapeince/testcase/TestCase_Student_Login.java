package technosapeince.testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import technosapeince.TestComponents.BaseTest;
import technosapeince.TestComponents.Retry;
import technosapeince.pageobjects.LoginPage;
import technosapeince.pageobjects.describeyoubest;

public class TestCase_Student_Login extends BaseTest {

	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void TestCase_Student(HashMap<String, String> data) throws Exception { // Valid Login

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asStudent();
		loginpage.login(data.get("email"), data.get("password"));

		String actualMessage = "";
		switch (data.get("errorMessageType")) {
		case "loginpage.geterrormessage4()":
			actualMessage = loginpage.geterrormessage4();
			break;
		case "loginpage.geterrormessage()":
			actualMessage = loginpage.geterrormessage();
			break;
		case "loginpage.geterrormessage1()":
			actualMessage = loginpage.geterrormessage1();
			break;
		case "loginpage.geterrormessage2()":
			actualMessage = loginpage.geterrormessage2();
			break;
		default:
			Assert.fail("Unknown error message type: " + data.get("errorMessageType"));
		}

		Assert.assertEquals(actualMessage, data.get("expectedMessage"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\technosapeince\\data\\StudentData.json");
		return data.stream().map(d -> new Object[] { d }).toArray(Object[][]::new);
	}

}
