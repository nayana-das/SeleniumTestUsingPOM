package com.tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupDriver() throws MalformedURLException {

        String host="localhost";
        MutableCapabilities dc ;

        if(System.getProperty("BROWSER")!=null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc=new FirefoxOptions();
        }
        else{
            dc=new ChromeOptions();
        }

        if(System.getProperty("HUB_HOST")!=null){
            host=System.getProperty("HUB_HOST");
        }

        String completeUrl="http://"+host+":4444/wd/hub";
        this.driver=new RemoteWebDriver(new URL(completeUrl),dc);
        this.driver.manage().window().maximize();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
