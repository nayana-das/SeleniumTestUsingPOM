package com.newtour.tests;

import com.newtour.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest {
    private WebDriver driver;
    private String noOfPassengers;
    private String expectedPrice;


    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setupDriver(String noOfPassengers,String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
        this.driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage =new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("test","selenium");
        registrationPage.enterUserCredentials("test","test@1234");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest(){
        RegistrationConfirmationPage rcPageTest = new RegistrationConfirmationPage(driver);
        rcPageTest.gotoFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void getFlightDetailsPageTest(){
        GetFlightDetailsPage getFlightDetailsPage=new GetFlightDetailsPage(driver);
        getFlightDetailsPage.selectPassengers(noOfPassengers);
        getFlightDetailsPage.gotoFindFlightsPage();
    }

    @Test(dependsOnMethods = "getFlightDetailsPageTest")
    public void findFlightsPageTest(){
        FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
        findFlightsPage.submitFindFlightPage();
        findFlightsPage.gotoFlightConfirmationPage();
    }

    @Test(dependsOnMethods ="findFlightsPageTest")
    public void findFlightConfirmationPageTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice=flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice,this.expectedPrice);
    }
    @AfterTest
    public void quitBrowser(){
        this.driver.quit();
    }
}
