package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObject_Search {
//step2
@FindBy(id="com.bigbasket.mobileapp:id/homePageSearchBox")
public WebElement searchbtn;

@FindBy(id="com.bigbasket.mobileapp:id/homePageSearchBox")
public WebElement searchbtn2;
	
@FindBy(id="com.bigbasket.mobileapp:id/txtProductCount")
public WebElement validmsg;

@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
public WebElement invalidmsg;

	//step1: initialize page factory

public PageObject_Search(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
//step3

public void Enterinput(String Search_Item)
{
	searchbtn.click();
	searchbtn2.sendKeys(Search_Item);
}
public String getvalidmsg()
{
	return validmsg.getText();
}
public String getinvalidmsg()
{
	return invalidmsg.getText();
}

}
