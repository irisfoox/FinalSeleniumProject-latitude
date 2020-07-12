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
		new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(search));
		search.click();
		search.clear();
		search.sendKeys(cityName);
		search.sendKeys(Keys.ENTER);
		}
	@SuppressWarnings("finally")
	public int clickTiberias() throws InterruptedException { 
		try {
		new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(clicker));		
		clicker.click();
		Thread.sleep(5000);  //only works with this
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}finally {
		new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOf(lat));	
	    String strLat=lat.getText();
	    System.out.println("The latitude for the Wikipedidia's search: "+strLat);  //check for self
	    return LatitudeTest.getLat(strLat);
		}
	}
}
