package com.qa.hs.keyword.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties prop;

    public WebDriver init_driver(String browserName){
       if(browserName.equals("chrome")){
           System.setProperty("webdriver.chrome.driver", "/home/umaS/Downloads/chromedriver_linux64/chromedriver");
               driver = new ChromeDriver();
       }

       return driver;

    }


    public Properties init_properties() throws IOException {
        prop= new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/com/qa/hs/keyword/scenarios/hubspot.scenarios .xlsx");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return prop;
    }
}
