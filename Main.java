package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.core.SubstringMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main implements TestData{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/Selenium/Drivers/chrome79/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        /*driver.get("https://www.google.com");
        driver.manage().window().maximize();
        WebElement search=driver.findElement(By.cssSelector("[name='q'][type='text']"));
        search.click();
        search.sendKeys("Tiberias Israel");
        search.sendKeys(Keys.ENTER);*/
        //search.clear();    //only works without this line
        List<String>URLList=Arrays.asList(UrlTiberias,UrlHaifa);
        WebDriverWait waitVar=new WebDriverWait(driver,10);
        List<String>latList=new ArrayList<String>();
        String most="";
        for(int i=0;i<URLList.size();i++) {
            driver.get(URLList.get(i));
            waitVar.until(ExpectedConditions.urlContains((URLList.get(i).substring(3))));
            WebElement latitude=driver.findElement(By.cssSelector("[class='latitude']"));
     	    System.out.println(latitude.getText());   //check for self
        	latList.add(String.format("%s%s",latitude.getText().charAt(0),latitude.getText().charAt(1)));
     	    System.out.println(latList.get(i));       //check for self
        }
		//driver.get(UrlJerusalem);    
		driver.get("https://latitudelongitude.org/");
		waitVar.until(ExpectedConditions.titleContains("latitude"));
		WebElement clicker=driver.findElement(By.linkText("Israel"));
		clicker.click();
		waitVar.until(ExpectedConditions.urlContains("Israel"));
		waitVar.wait(3000);
		clicker=driver.findElement(By.cssSelector("[href='jerusalem']"));
		clicker.click();    //why not working?
		//waitVar.wait(3000);
		//waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[style='white-space: nowrap; border:1px solid #e85151; padding:4px;']")));
		waitVar.until(ExpectedConditions.titleContains("Jerusalem"));
		WebElement lat=driver.findElement(By.cssSelector("[style='white-space: nowrap; border:1px solid #e85151; padding:4px;']"));
		System.out.println(lat.getText());      //check for self
		waitVar.until(ExpectedConditions.titleContains("Jerusalem"));
		latList.add(String.format("%s%s",lat.getText().charAt(0),lat.getText().charAt(1)));
		System.out.println(latList.get(2));     //check for self
		
        
		latitudeToString(getLatitudeDiff(latList,URLList, most),most,URLList,latList);
		waitVar.wait(3000);
		driver.close();
	}
	
	public static int getLatitudeDiff(List<String>latList,List<String>URLList,String most) {
		// TODO Auto-generated method stub
		int diff=0; int max=0; 
		
		List<Integer>latInt=new ArrayList<Integer>();
		for(int i=0;i<latList.size();i++) {
			latInt.add(Integer.parseInt((latList.get(i))));		//casting into int list
			if(latInt.get(i)>max) {
				max=latInt.get(i);
				most=URLList.get(i).substring(3);
			}
			}
		Collections.sort(latInt);           //sorting list in ascending order to get diff
		int i=latInt.size()-1;
		if(latInt.get(i)>latInt.get(i-1)) {
				diff=latInt.get(i)-latInt.get(i-1);
			}
		else if(latInt.get(i)==latInt.get(i-1)) {
			diff=0;
		}
		else {System.out.println("Error in format");}
		
		return diff;
	}
	public static void latitudeToString(int diffrence,String most,List<String>URLList,List<String>latList) {
		// TODO Auto-generated method stub
		for(int i=0;i<URLList.size();i++) {
		   System.out.println(String.format("The city % is in the latitude of %s degrees",URLList.get(i).substring(3)
				   ,latList.get(i)));
		}	   
		System.out.println(String.format("The most northern city is %s by the diffrence of %s from the closest city",
				most,diffrence));
	}

}
