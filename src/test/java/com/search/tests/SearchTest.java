package com.search.tests;

import com.search.pages.SearchPage;
import com.tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private WebElement keyword;
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


}
