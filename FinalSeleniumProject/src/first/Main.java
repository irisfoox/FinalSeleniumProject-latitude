package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main implements TestData{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/Selenium/drivers/chrome83/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        /*driver.get("https://www.google.com");
        driver.manage().window().maximize();
        WebElement search=driver.findElement(By.cssSelector("[name='q'][type='text']"));
        search.click();
        search.sendKeys("Tiberias Israel");
        search.sendKeys(Keys.ENTER);*/
        
        List<String>URLList=Arrays.asList(UrlTiberias,UrlHaifa);
        WebDriverWait waitVar=new WebDriverWait(driver,10);
        List<String>latList=new ArrayList<String>();
        
        String most=" ";
        for(int i=0;i<URLList.size();i++) {
            driver.get(URLList.get(i));
            waitVar.until(ExpectedConditions.urlContains((URLList.get(i).substring(3))));
            WebElement latitude=driver.findElement(By.className("latitude"));
     	    System.out.println(latitude.getText());   //check for self
        	latList.add(latitude.getText().substring(0, 2));
     	    System.out.println(latList.get(i));       //check for self
        }
		   
		driver.get("https://latitudelongitude.org/");
		waitVar.until(ExpectedConditions.titleContains("latitude"));
		WebElement clicker=driver.findElement(By.xpath("//*[@id='content']/section[2]/div/a[149]"));
		clicker.click();
		waitVar.until(ExpectedConditions.urlContains("https://latitudelongitude.org/il"));
		clicker=driver.findElement(By.xpath("//*[@id='content']/section[2]/div/b[3]/a"));
		clicker.click();    
		waitVar.until(ExpectedConditions.urlContains("https://latitudelongitude.org/il/jerusalem"));
		WebElement lat=driver.findElement(By.xpath("//*[@id=\'content\']/section[1]/p/span"));
		System.out.println(lat.getText());      //check for self
		waitVar.until(ExpectedConditions.visibilityOf(lat));
		latList.add(lat.getText().substring(0,2));
		System.out.println(latList.get(2));     //check for self
		
   		getLatitudeDiff(latList,URLList, most);
		
		Thread.sleep(2000);
		driver.close();
		System.out.println("### Test End ###");
	}
	
	public static void getLatitudeDiff(List<String>latList,List<String>URLList,String most) {
		// TODO Auto-generated method stub
		int max=0,min=100; 
		
		List<Integer>latInt=new ArrayList<Integer>();
		for(int i=0;i<latList.size();i++) {
			latInt.add(Integer.parseInt((latList.get(i))));		//casting into int list
			if(latInt.get(i)>max) {
				max=latInt.get(i); 
			}
			if(latInt.get(i)<min) {
				min=latInt.get(i);
		}	
		}
		for(int i=0;i<latInt.size();i++) {
			if(max==latInt.get(i)){
			     most=URLList.get(i).substring(URLList.get(i).lastIndexOf("/")+1);            //name of northest city
				}
			}
		 ToString(most,max,min);
	
	}
	public static void ToString(String most,int max,int min) {
		// TODO Auto-generated method stub
	    System.out.println(String.format("The northest city is %s in the latitude of %s degrees",most,max));
		System.out.println(String.format("The biggest latitude diffrence between the northest city"
				+ " and the most southern one is %s ",max-min));
		
		 
	}

}
