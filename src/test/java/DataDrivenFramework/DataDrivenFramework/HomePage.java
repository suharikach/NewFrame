package DataDrivenFramework.DataDrivenFramework;

import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.FlightSearch;
import pages.LandingPage;
import pages.SearchResults;
import resource.Base;
import resource.Config;

public class HomePage extends Base {
	public static Logger log=LogManager.getLogger(Base.class.getName());
	WebDriver driver;

	Config conf= new Config();
	@BeforeTest
	public void initiBrow()
	{
		driver=initializeDriver();
		driver.get(conf.Properties("url"));
		log.info("Initialized");
		
	}
	
	@Test(dataProvider = "Data")
	public void test(String from, String to, String ExpectedM )
	{
		
		LandingPage lp=new LandingPage(driver);
		log.info("Landed in home page");
		FlightSearch fs= new FlightSearch(driver);
		//log.info("Entered the search criteria");
		SearchResults sr=new SearchResults(driver);
		//log.info("Search results displayed");
		
		lp.Landingpage();
		fs.searchCriteria(from,to, ExpectedM);
		boolean result=	sr.FLightResults();
		Assert.assertTrue(result);
		
	}
	@AfterTest
	public void close()
	{
		driver.close();
	}
	
@DataProvider
public Object[][] Data()
{
	Object[][] data= new Object[2][3];
	data[0][0]="HYD";
	data[0][1]="DEL";
	data[0][2]="September 2020";
	data[1][0]="DEL";
	data[1][1]="HYD";
	data[1][2]="September 2020";
	return data;
	
	
	
}

}
