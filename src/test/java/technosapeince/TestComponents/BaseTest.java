package technosapeince.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import technosapeince.pageobjects.Homepage;

public class BaseTest {
	public WebDriver driver;
	public Homepage homepage;

	public WebDriver intializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\tecnosapeince\\resources\\Globaldata.properties");
		prop.load(fis);
		
		
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		 //prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}


  

	public String getScreenshot(String methodName) {
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String destPath = System.getProperty("user.dir") + "/reports/screenshots/" + methodName + ".png";
	    try {
	        FileUtils.copyFile(srcFile, new File(destPath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return destPath;
	}


	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// Reading Json to String
		String jsoncontent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Reading String to HashMap (Jackoson Datbind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public Homepage launchapplication() throws Exception {

		driver = intializeDriver();
		homepage = new Homepage(driver);
		homepage.goTo();
		homepage.clickCancelButton();
		return homepage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}

}
