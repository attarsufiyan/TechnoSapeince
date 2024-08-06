package technosapeince.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import technosapeince.AbstractComponents.AbstractComponents;

public class Homepage extends AbstractComponents {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//-------------------------------------------------------------//
	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement cancelButton;

	@FindBy(css = ".css-9ajahe")
	List<WebElement> category;

	@FindBy(css = ".css-9ajahe")
	List<WebElement> subcategory;

	@FindBy(css = ".css-1scstjv")
	WebElement siginbutton;

	@FindBy(css = ".css-1yxmbwk")
	WebElement searchicon;

	@FindBy(css = ".css-1xvinid")
	WebElement checkresult;
	
	

//-----------------------------------------------------------//

	public void clickCancelButton() {
		cancelButton.click();
	}

	public void goTo() {
		driver.get("https://technobug.in/");
	}

	public List<WebElement> getCategory() {

		return category;

	}

	public WebElement getCategoryName(String Category) {
		WebElement cat = getCategory().stream().filter(categorys -> categorys.getText().equals(Category)).findFirst()
				.orElse(null);
		return cat;
	}

	public void catClick(String Category) {

		WebElement cat = getCategoryName(Category);
		cat.click();
	}

	public List<WebElement> getSubCategory() {

		return subcategory;

	}

	public void getSubCategoryName(String SubCategory) {
		WebElement subcat = getSubCategory().stream().filter(subcategorys -> subcategorys.getText().equals(SubCategory))
				.findFirst().orElse(null);

		subcat.click();

	}

	public CourseCatalogue subClick(String SubCategory) {

		WebElement subcat = getCategoryName(SubCategory);
		subcat.click();
		CourseCatalogue coursecatalogue = new CourseCatalogue(driver);
		return coursecatalogue;
	}

	public describeyoubest signIn() {

		siginbutton.click();
		describeyoubest select = new describeyoubest(driver);
		return select;
	}

	public boolean searchBarDisplayed() {
		waitWebElementToAppear(searchBar());
		return searchBar().isDisplayed();
	}

	public boolean searchBarEnabled() {
		waitWebElementToAppear(searchBar());
		return searchBar().isEnabled();
	}

	public boolean searchbartc_1() throws InterruptedException {

		searchBarinput().sendKeys("                     ");
		searchicon.click();
		String result = checkresult.getText();
		return result.contains("28");
	}

}
