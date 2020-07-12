package SeleniumTests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumPOM.LatSearchCity;
import SeleniumPOM.LatSearchCountry;
import SeleniumPOM.WikiSearch;
import first.TestData;

public class LatitudeTest implements TestData{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		    System.setProperty("webdriver.chrome.driver", "C://Program Files//Selenium//drivers//chrome83//chromedriver.exe");
		    WebDriver driver=new ChromeDriver();
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);   //implicityWait

		    WikiSearch wiki=new WikiSearch(driver);
		    LatSearchCountry latCountry=new LatSearchCountry(driver);
		    LatSearchCity latCity=new LatSearchCity(driver);
		    Map<String,Integer>latMap=new HashMap<String,Integer>();         //using Map
		    
		    driver.get(URLWikipedia);                               //using Interface
		    wiki.searchCity("Tiberias");
		    latMap.put("Tiberias",wiki.clickTiberias());
		    
		    driver.get(URLLatitude);
		    new WebDriverWait(driver,15).until(ExpectedConditions.urlContains("latitudelongitude"));
		    latCountry.searchCountry("United Kingdom");
		    latCity.searchCity("London");
		    latMap.put("London",latCity.clickCity());
		    
		    latCity.home();
		    latCountry.searchCountry("United States");
		    latCity.searchCity("New York City");
		    latMap.put("NewYork",latCity.clickCity());
		    
		    System.out.println("Each city & its latitude: "+latMap);    
		    getNorth(latMap);
		    
		    Thread.sleep(5000);
		    driver.close();
		    System.out.println("### Test End ###");
		
	}
	public static int getLat(String strLat) {
		
		int lat=Integer.parseInt(strLat.substring(0, 2));
		return lat;
	}
	public static void getNorth(Map<String, Integer> latMap) {
		int max=0,min=100;
	    Iterator<Map.Entry<String,Integer>>itr=latMap.entrySet().iterator();
	    while(itr.hasNext()) {
	    	Map.Entry<String,Integer>entry=itr.next();
		        if(entry.getValue()>max) {
		        	max=entry.getValue();
		        }
		        if(entry.getValue()<min) {
		        	min=entry.getValue();
		        }
		    }
	    Iterator<Map.Entry<String,Integer>>it=latMap.entrySet().iterator();
	    while(it.hasNext()) {
	    	Map.Entry<String,Integer>entry=it.next();
		        if(entry.getValue()==max) {	
		    		System.out.println(String.format("The northest latitude is: %s",entry.getValue()));
		    		System.out.println(String.format("The northest city is: %s",entry.getKey()));
		        }
		    	if(entry.getValue()==min) {	
		    		System.out.println(String.format("The southest city is %s by %s degrees from the northest city"
		    				,entry.getKey(),max-min));       //math manipulation
		    	}
		    }
	}

}
