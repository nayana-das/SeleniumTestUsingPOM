package com.search.tests;

import com.search.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest {

    private WebDriver driver;
    private WebElement keyword;

    @BeforeTest
    public void setupDriver(){
        this.driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage= new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int actualListSize=searchPage.getResult();

        Assert.assertTrue(actualListSize>0);
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
