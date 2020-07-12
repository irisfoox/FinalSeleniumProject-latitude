package SeleniumPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LatSearchCountry {

	private WebDriver driver;
	    
	//Constructor
	public LatSearchCountry(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void searchCountry(String country) throws InterruptedException {
		WebElement search=driver.findElement(By.linkText(country));
		new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(search));
		search.click();
		Thread.sleep(5000);   //only works with this
		
	}
}
