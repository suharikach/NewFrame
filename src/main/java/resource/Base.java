package resource;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	public static WebDriver driver;
	Config conf= new Config();
public WebDriver initializeDriver()
{
	System.out.println(conf.Properties("browser"));
	if (conf.Properties("browser").equalsIgnoreCase("chrome"))
	{
	System.setProperty("webdriver.chrome.driver", "C:/Users/sai.suharika.chakka/suharika/selenium/chromedriver_win32/chromedriver83.exe");
	driver=new ChromeDriver();
	}
	else
	{
		System.setProperty("WebDriver.Chrome.Driver", "C:/Users/sai.suharika.chakka/suharika/selenium/chromedriver_win32/chromedriver83.exe");
		driver=new ChromeDriver();
		System.out.println("Please change browser to chrome");
	}
	driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
//all test cases will wait for 30 secs
return driver;
}
public void getScreenshot(String MethodName, WebDriver driver)
{
	Date d= new Date();
	SimpleDateFormat sd= new SimpleDateFormat("M_dd_YYYY");
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	File dest= new File(System.getProperty("user.dir")+"/Screenshot/selenium"+MethodName+".png");
//FileHandler.copy(src,dest);
	try {
		FileUtils.copyFile(src,dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}


}
