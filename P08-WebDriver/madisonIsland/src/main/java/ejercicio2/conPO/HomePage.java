package ejercicio2.conPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class HomePage{
    private WebDriver driver;
    private WebElement webbtn_acount;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com");
        webbtn_acount = this.driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a"));
    }

    public CustomerLoginPage loginPage(){
        webbtn_acount.click();
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a")).click();
        return new CustomerLoginPage(driver);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}