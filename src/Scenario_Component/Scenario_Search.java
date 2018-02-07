package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import Generic_Component.Base_Class;
import PageObject_Component.PageObject_Search;

public class Scenario_Search extends Base_Class {

	public static Logger log= Logger.getLogger(Scenario_Search.class);
//private static final Object Exp_Result = null;



@Test(dataProvider="dp_invalidSearch",dataProviderClass=DataProvider_Component.TestDataProvider.class)
public void testInvalidSearch(Map<String,String> search) throws IOException, InterruptedException
{
	
	String Exp_Result= search.get("Exp_Result");
	String TC_ID=search.get("TC_ID");
	String Script_Name = search.get("Script_Name");
	String Search_Item = search.get("Search_Item");
	String Order_Set= search.get("Order_Set");
	SoftAssert sAssert= new SoftAssert();
	
	//Start_server();
	Launch_App();
	log.info("Started executing test case " + TC_ID + "order set is " + Order_Set);
	
	extenttest= extentreports.starttest(TC_ID);
	extenttest.log(LogStatus.PASS("Started executing test case \" + TC_ID + \"order set is \" + Order_Set);"))
	PageObject_Search PO_S = new PageObject_Search(driver);
	Explicit_wait(PO_S.searchbtn,30);
	
	PO_S.Enterinput(Search_Item);
	String Act_Result= PO_S.getinvalidmsg();
	
	if(Act_Result.equals(Exp_Result))
		{
			System.out.println("Pass");
			log.info("Test case passed as Actual result is " + Act_Result + " Expected result is " + Exp_Result);
		extenttest.log(logstatus.pass());
		}
		else
		{
			System.out.println("Fail");
			sAssert.fail("Test case Failed as Actual result is \" + Act_Result + \" Expected result is \" + Exp_Result);");
			log.info("Test case Failed as Actual result is " + Act_Result + " Expected result is " + Exp_Result);
			Capture_Screenshot(TC_ID,Order_Set);
			extenttest.log(logstatus.fail());
		}
	Stop_server();
	sAssert.assertAll();
	
	}

}
