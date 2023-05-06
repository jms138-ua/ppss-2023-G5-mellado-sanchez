package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

public class TestShoes{
    public WebDriver driver;
    public MyAccountPage myAccountPage;

    @BeforeEach
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(Boolean.parseBoolean(System.getProperty("chromeHeadless")));
        driver = new ChromeDriver(chromeOptions);

        Cookies.storeCookiesToFile("jms138@ua.com", "uappss");
        Cookies.loadCookiesFromFile(driver);

        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void compareShoes(){
        assertEquals("My Account", myAccountPage.getPageTitle());

        ShoesPage shoesPage = myAccountPage.accessShoesPage();

        assertEquals("Shoes - Accessories", shoesPage.getPageTitle());

        shoesPage.selectShoeToCompare(5);
        shoesPage.selectShoeToCompare(6);
        ProductComparisonPage productComparisonPage = shoesPage.submitCompare();

        assertEquals("Products Comparison List - Magento Commerce", productComparisonPage.getPageTitle());

        shoesPage = productComparisonPage.close();

        assertEquals("Shoes - Accessories", shoesPage.getPageTitle());

        shoesPage.ClearComparison();

        assertEquals("The comparison list was cleared.", shoesPage.getResultStatus());
    }
}