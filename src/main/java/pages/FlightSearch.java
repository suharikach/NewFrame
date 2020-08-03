package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.Argument;
import utility.Helper;

public class FlightSearch {
	WebDriver driver;
	By FlightName = By.xpath("//div[@class='pull-left airways-info-sect']//p[@class='fli-code']");
	By FlightPRC = By.xpath("//div[@class='dept-options-section clearfix']//span[@class='actual-price']");
	By search = By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn' or text()='Search']");
	By login = By.xpath("//li[@data-cy='account']");
	By FlightButton = By.xpath("//a[@class='active makeFlex hrtlCenter column']");
	By From = By.xpath("//input[@data-cy='fromCity']");
	By To = By.xpath("//input[@data-cy='toCity']");
	// By To= By.xpath("//div[@class='fsw_inputBox searchToCity inactiveWidget
	// activeWidget']//input[@aria-controls='react-autowhatever-1']");
	By FromLocation = By.xpath("//input[@placeholder='From']");
	By ToLocation = By.xpath("//input[@placeholder='To']");
	By ListValue = By.xpath("//div[@class='pushRight font14 lightGreyText latoBold']");
	String url = "https://www.makemytrip.com/";
	By DepDate = By.xpath("//p[@data-cy='departureDate']");
	By month = By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]");
	By NextMonth = By.xpath("//span[@aria-label='Next Month']");
	By day = By.xpath("//div[@class='dateInnerCell']/p[text()='15'][1]");

	public FlightSearch(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	public void searchCriteria(String fromcity, String tocity, String expectedM)
	{

		Helper.FindAndClick(driver, FlightButton);
		Helper.FindAndEnter(driver, From, fromcity);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> options = Helper.Findlist(driver, ListValue);

		for (WebElement op : options) {
			System.out.println(op.getText());
			if (op.getText().equalsIgnoreCase(fromcity)) {
				op.click();
				System.out.println("Click performed");
				break;
			}
		}
		JavascriptExecutor Je= (JavascriptExecutor)driver;
		Je.executeScript("arguments[0].setAttribute('value','"+tocity+"')",driver.findElement(To));
		//Helper.FindAndEnter(driver, To, tocity);
		//driver.findElement(To).sendKeys(tocity);
		List<WebElement> options1 = driver.findElements(ListValue);
		for (WebElement op : options1) {
			if (op.getText().equalsIgnoreCase(tocity)) {
				op.click();
				break;
			}
		}

		CalDate(expectedM);
		Helper.FindAndClick(driver, search);
		// driver.findElement(search);
		//Thread.sleep(3000);
		//FLightResults();
	}

	public void CalDate(String expectedM) {
		driver.findElement(DepDate).click();
		String ExpectedMonth =expectedM;
		String ActMon = driver
				.findElement(By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]")).getText();
		while ((ExpectedMonth.equalsIgnoreCase(ActMon)) != true) {
			ActMon = driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div/div[@role='heading'][1]/div[1]"))
					.getText();
			if (ExpectedMonth.equalsIgnoreCase(ActMon)) {
				break;
			}

			driver.findElement(NextMonth).click();
		}
		driver.findElement(day).click();

	}




}
