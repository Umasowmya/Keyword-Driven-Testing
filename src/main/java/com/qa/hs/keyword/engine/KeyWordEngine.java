package com.qa.hs.keyword.engine;

import com.qa.hs.keyword.base.Base;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class KeyWordEngine {
    public WebDriver driver;

    public Properties prop;

    public WebElement element;
    public static  Base base;
    public final String SCENARIO_SHEET_PATH = "src/main/java/com/qa/hs/keyword/scenarios/hubspot.scenarios .xlsx";

    public static Workbook book;
    public static Sheet sheet;

    String locatorName=null;
    String locatorValue=null;
    public void startExecution(String sheetName) throws IOException, InterruptedException {
        FileInputStream file=null;
        try{
        file= new FileInputStream(SCENARIO_SHEET_PATH);}
        catch(Exception e){
            e.printStackTrace();
        }
        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);
        int k=0;

//        System.out.println(book.getSheet("login").getLastRowNum());
        for(int i=0;i<sheet.getLastRowNum();i++){
           String locator =  sheet.getRow(i+1).getCell(k+1).toString().trim();
           if(!locator.equalsIgnoreCase("NA")){
               locatorName = locator.split("=")[0].trim();
               locatorValue = locator.split("=")[1].trim();
           }

           String action  = sheet.getRow(i+1).getCell(k+2).toString().trim();
           String value  = sheet.getRow(i+1).getCell(k+3).toString().trim();

            base = new Base();
            prop = base.init_properties();
           switch (action){
               case "open browser":

                   if(value.isEmpty() || value.equalsIgnoreCase("NA")){
                       driver=base.init_driver(prop.getProperty("browser"));

                   }
                   else{
                       driver=base.init_driver(value);
                   }


                   break;

               case "enter url":

                   if(value.isEmpty() || value.equalsIgnoreCase("NA")){
                       driver.get(prop.getProperty("url"));
                   }
                   else{
                       driver.get(value);
                   }
                   Thread.sleep(3000);

                   break;

               case "quit":
                   driver.quit();
                   break;


               default:break;
           }


           switch (locator){
               case "id":
                   element = driver.findElement(By.id(locatorValue));
                   if(action.equalsIgnoreCase("sendKeys")){
                       element.clear();
                       element.sendKeys(value);
                   }

                   else if(action.equalsIgnoreCase("click")){
                       element.click();
                   }
                   Thread.sleep(3000);
                   break;

               case "linkText":
                   element = driver.findElement(By.linkText(locatorValue));
                       element.click();
                   Thread.sleep(3000);

                   break;


           }
        }
    }
}
