package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	public static void FindAndClick(WebDriver driver, By ele) {
		
		WebDriverWait wait= new WebDriverWait(driver, 20);
		//WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();
	}

	public void FindAndClick(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.click();
	}

	public static void FindAndEnter(WebDriver driver, By ele, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(ele));
		elem.sendKeys(value);
	}

	public static List<WebElement> Findlist(WebDriver driver, By ele) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
		return elem;
	}
}
