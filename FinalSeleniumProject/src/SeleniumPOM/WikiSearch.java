package SeleniumPOM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTests.LatitudeTest;

public class WikiSearch {
    
	private WebDriver driver;
	@FindBy(id="searchInput")
	    WebElement search;
	@FindBy(xpath="//*[@id='mw-content-text']/div[3]/ul/li[1]/div[1]/a/span")  //using xpath
	    WebElement clicker;
	@FindBy(className="latitude")
	    WebElement lat;
	//Constructor
	public WikiSearch(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void searchCity(String cityName) {
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(search));
		search.click();
		search.clear();
		search.sendKeys(cityName);
		search.sendKeys(Keys.ENTER);
		}
	public int clickTiberias() throws InterruptedException { 
		
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(clicker));		
		clicker.click();
		Thread.sleep(2000);
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(lat));
		//Thread.sleep(2000);
	    //WebElement lat=driver.findElement(By.xpath("//*[@id='mw-content-text']/div/table[1]/tbody/tr[7]/td/span[1]/span/a/span[1]/span/span[1]"));
		//new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(lat));		
	    String strLat=lat.getText();
	    System.out.println(strLat);  //check for self
	    return LatitudeTest.getLat(strLat);
	}
}
