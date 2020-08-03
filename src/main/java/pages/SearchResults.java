package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResults 
{
WebDriver driver;
By FlightName = By.xpath("//div[@class='pull-left airways-info-sect']//p[@class='fli-code']");
By FlightPRC = By.xpath("//div[@class='dept-options-section clearfix']//span[@class='actual-price']");
By search = By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn' or text()='Search']");
public SearchResults(WebDriver driver)
{
	this.driver=driver;
}
	public int TotalFlights() {
		List<WebElement> DifFlights = driver
				.findElements(By.xpath("//p[text()='Airlines']/following-sibling::div //div[@class='flexOne']/p"));
		int Total = 0;
		for (WebElement fli : DifFlights) {
			String num = fli.getText();
			String a[] = num.split("[()]");
			int value = Integer.parseInt(a[1]);
			Total = Total + value;
		}

		System.out.println("Total flights based on criteria are " + Total);
		return Total;
	}

	public boolean FLightResults() {

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		int count = TotalFlights();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		System.out.println("Printing the flight results");
		List<WebElement> FName = driver.findElements(FlightName);
		List<WebElement> FPRC = driver.findElements(FlightPRC);
		int TotalFlights = FName.size();
		while (TotalFlights < count) {
			WebElement ele = FName.get(TotalFlights - 1);
			js.executeScript("arguments[0].scrollIntoView();", ele);
			FName = driver.findElements(FlightName);
			FPRC = driver.findElements(FlightPRC);
			TotalFlights = FName.size();
		}
		int prc[] = new int[TotalFlights];
		for (int i = 0; i < TotalFlights; i++) {
			String k = FName.get(i).getText();
			String j = (String) FPRC.get(i).getText().subSequence(2, 7);
			String l = j.replace(",", "");
			prc[i] = Integer.parseInt(l);
			System.out.println(i + "The FlightName is" + k + "price is " + prc[i]);
		}
		// find the least value

		int index=0, first = prc[0];
		for (int i = 1; i <TotalFlights; i++) {
			if (prc[i] < first) {
				first = prc[i];
				index = i;
			}
			
		}
	System.out.println("Flight with low fare is"+FName.get(index).getText()+"  Lost fair is "+FPRC.get(index).getText());
return true;
	}
}

