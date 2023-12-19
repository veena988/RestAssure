package RestAssure.Testing;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import  io.restassured.RestAssured;
import io.restassured.response.Response; 


public class APITesting {
	
	  @Test(dataProvider = "ExcelValue")
	    public void testGet(String country ) { 
	        RestAssured.baseURI = "https://restcountries.com/v3.1/translation/"; 
	       
	      
	        	System.out.println(country);
		        Response response = RestAssured.get(country); 
		     
		        // Verify that the response has a 200 OK status code 
		        response.then().assertThat().statusCode(200); 
	    
	   
	    } 
	  @DataProvider (name = "ExcelValue") 
	  public Object[] readExcel()
	  
	  {
		  String country1 = null,country2 = null ,country3=null;
		 ArrayList data =new ArrayList();
		  try
	        {
			
	            FileInputStream file = new FileInputStream("src\\main\\java\\Read.xlsx");
	            XSSFWorkbook wb = new XSSFWorkbook(file);
	            XSSFSheet ws = wb.getSheetAt(0);
	            Iterator<Row> rowIterator = ws.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    switch (cell.getCellType()) 
	                    {
	                        case NUMERIC:
	                            cell.getNumericCellValue();
	                            data.add(cell.getNumericCellValue());
	                            break;
	                        case STRING:
	                            cell.getStringCellValue();
	                            data.add(cell.getStringCellValue());
	                            break;
	                    }
	                }
	                System.out.println();
	                
	            }
	            country1 = (String) data.get(0);
	            country2 = (String) data.get(1);
	            country3 = (String) data.get(2);
		        file.close();
	        } 
	        catch (Exception ex) 
	        {
	            ex.printStackTrace();
	        }
		  return new Object[] {country1,country2,country3};
	  }

}
