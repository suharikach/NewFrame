package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
	WebDriver driver;
	By EmailPopup=By.xpath("//section[@class='modalMain ']");
	By popup = By.xpath("//div[@class='autopop__wrap makeFlex column defaultCursor']");
	By login = By.xpath("//li[@data-cy='account']");
	//public
	public LandingPage(WebDriver driver){
		this.driver=driver;
	}
	
public void Landingpage()
{
	System.out.println("Landing");
	if (driver.findElements(popup) != null)
	{
		driver.findElement(login).click();
	}
	else if (driver.findElement(EmailPopup)!=null)
	{
	//t	driver.findElement(popup).click();
		driver.findElement(login).click();
		
		
	}
}
}
