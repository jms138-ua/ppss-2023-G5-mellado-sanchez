package ejercicio2.conPO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin2{
    private WebDriver driver;
    private HomePage homePage;
    private CustomerLoginPage customerLoginPage;
    private MyAccountPage myAccountPage;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test_Login_Correct(){
        assertEquals("Madison Island", homePage.getPageTitle());
        customerLoginPage = homePage.loginPage();
        assertEquals("Customer Login", customerLoginPage.getPageTitle());
        myAccountPage = customerLoginPage.loginOK("jms138@ua.com","uappss");
        assertEquals("My Account", myAccountPage.getPageTitle());
    }

    @Test
    public void test_Login_Incorrect(){
        assertEquals("Madison Island", homePage.getPageTitle());
        customerLoginPage = homePage.loginPage();
        assertEquals("Customer Login", customerLoginPage.getPageTitle());
        assertEquals("Invalid login or password.", customerLoginPage.loginFail("jms138@ua.com","badpasswd"));
    }
}