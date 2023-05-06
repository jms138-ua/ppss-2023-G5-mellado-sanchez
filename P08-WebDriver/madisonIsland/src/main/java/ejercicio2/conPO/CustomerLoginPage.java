package ejercicio2.conPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class CustomerLoginPage{
    private WebDriver driver;
    private WebElement wein_email, wein_passwd, webtn_login;

    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
        wein_email = driver.findElement(By.id("email"));
        wein_passwd = driver.findElement(By.id("pass"));
        webtn_login = driver.findElement(By.cssSelector("form#login-form"));
    }

    public MyAccountPage loginOK(String email, String passwd){
        wein_email.sendKeys(email);
        wein_passwd.sendKeys(passwd);
        webtn_login.submit();
        return new MyAccountPage(driver);
    }

    public String loginFail(String email,String passwd){
        wein_email.sendKeys(email);
        wein_passwd.sendKeys(passwd);
        webtn_login.submit();
        return driver.findElement(By.className("error-msg")).getText();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}