package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class TestDataProvider {

@DataProvider(name="dp_validSearch")
public static Iterator<Object[]> getvalidsearchdata() throws IOException
{
	return getcommontestdata("Scenario_Search","Valid_Search");
}
@DataProvider(name="dp_invalidSearch")
public static Iterator<Object[]> getinvalidsearchdata() throws IOException
{
	return getcommontestdata("Scenario_Search","Invalid_Search");
}
public static Iterator<Object[]> getcommontestdata(String sheetname, String scriptname) throws IOException
{
	ExcelReadWrite xl= new ExcelReadWrite("D:\\Mobile easylearn\\Framework_Data\\TestData\\Test_Data.xls");
	HSSFSheet setsheet = xl.Setsheet(sheetname);
	
	int Rowcount = xl.getrowcount(setsheet);
	System.out.println("Row count is " + Rowcount);
	int Colcount = xl.getcolcount(setsheet, 0);
	System.out.println("Column count is " + Colcount);
	
	List<Object[]> arr_list= new ArrayList<Object[]>();
	
	for(int i=1; i<=Rowcount; i++)
	{	
	String Script_Name = xl.Readvalue(setsheet, i, "Script_Name");
	String Execute_Flag = xl.Readvalue(setsheet, i, "Execute_Flag");
	
	if((Script_Name.equals(scriptname)) && (Execute_Flag.equals("Y")))
	{
		Map<String,String> hmap= new HashMap<String,String>();
		Object [] x= new Object[1];
		for( int j=0;j<=Colcount; j++)
		{
			String key = xl.Readvalue(setsheet, 0, Colcount);
			String value = xl.Readvalue(setsheet, i, Rowcount);
			hmap.put(key, value);
	}//end of for loop
	x[0]=hmap;
	arr_list.add(x);
	}//end of if loop
	
	}//end of outer for loop
	return arr_list.iterator();
}
}
