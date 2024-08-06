package SampleTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import technosapeince.TestComponents.BaseTest;
import technosapeince.pageobjects.LoginPage;
import technosapeince.pageobjects.describeyoubest;

public class TestCase_Educator_Login extends BaseTest {

	@Test
	public void testCase_1() throws Exception { // Valid Login

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@educator.com", "educatorpassword");
		Assert.assertEquals("Signin Success.", loginpage.geterrormessage4());

	}

	@Test
	public void testCase_2() throws Exception {// Case Sensitivity Check

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("EDUCATOR@EDUCATOR.COM", "educatorpassword");
		Assert.assertEquals("Signin Success.", loginpage.geterrormessage4());

	}

	@Test
	public void testCase_3() throws Exception {// Incorrect email

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@student.com", "educatorpassword");
		Assert.assertEquals("Invalid credentials", loginpage.geterrormessage());

	}

	@Test
	public void testCase_4() throws Exception {// Incorrect Password

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@educator.com", "educatorpasswd");
		Assert.assertEquals("Invalid credentials", loginpage.geterrormessage());

	}

	@Test
	public void testCase_5() throws Exception {// Incorrect email

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@student.com", "educatorpassword");
		Assert.assertEquals("Invalid credentials", loginpage.geterrormessage());

	}

	@Test
	public void testCase_6() throws Exception {// Empty Email and Password

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("", "");
		Assert.assertEquals("Both Email and Password are required", loginpage.geterrormessage1());

	}

	@Test
	public void testCase_7() throws Exception {// Empty Email

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("", "educatorpassword");
		Assert.assertEquals("Both Email and Password are required", loginpage.geterrormessage1());

	}

	@Test
	public void testCase_8() throws Exception {// Empty Password

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@educator.com", "");
		Assert.assertEquals("Both Email and Password are required", loginpage.geterrormessage1());

	}

	@Test
	public void testCase_9() throws Exception {// SQL Injection Attempt

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@educator.com", "' OR '1'='1");
		Assert.assertEquals("Invalid credentials", loginpage.geterrormessage());
	}

	@Test
	public void testCase_10() throws Exception {// Cross-Site Scripting (XSS) Attempt

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educator@educator.com", "<script>alert('XSS')</script>");
		Assert.assertEquals("Invalid credentials", loginpage.geterrormessage());
	}

	@Test
	public void testCase_11() throws Exception {// Please enter a Invalid Email address

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educatro@educatorcom", "educatorpassword");
		Assert.assertEquals("Please enter a valid Email address", loginpage.geterrormessage2());
	}

	@Test
	public void testCase_12() throws Exception {// Please enter a Invalid Email address

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("educatoreducator.com", "educatorpassword");
		Assert.assertEquals("Please enter a valid Email address", loginpage.geterrormessage2());
	}

	@Test
	public void testCase_13() throws Exception {// Please enter space in Email and Password

		describeyoubest select = homepage.signIn();
		LoginPage loginpage = select.asEducator();
		loginpage.login("              ", "                    ");
		Assert.assertEquals("Please enter a valid Email address", loginpage.geterrormessage2());
	}

}
