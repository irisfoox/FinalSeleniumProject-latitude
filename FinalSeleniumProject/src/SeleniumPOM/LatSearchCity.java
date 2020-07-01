package SeleniumPOM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTests.LatitudeTest;

public class LatSearchCity {

	private WebDriver driver;
	//Constructor
	public LatSearchCity(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);   //using pageFactory
	}
	public void searchCity(String City) throws InterruptedException {
		WebElement search=driver.findElement(By.partialLinkText(City));         //using partialLinkText
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(search));
		search.click();
		Thread.sleep(2000);
	}
	public int clickCity() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);         //implicityWait
		WebElement lat=driver.findElement(By.xpath("//*[@id='content']/section[1]/p/span")); //using xpath
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(lat));
	    return LatitudeTest.getLat(lat.getText());
	}
	public void home() {
		WebElement home=driver.findElement(By.linkText("Home"));
		home.click();
	}
	
}
