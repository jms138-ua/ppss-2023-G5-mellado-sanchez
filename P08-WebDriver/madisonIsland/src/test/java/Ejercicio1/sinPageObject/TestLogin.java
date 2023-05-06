package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class TestLogin{
    private WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo-store.seleniumacademy.com");
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @Test
    public void loginOK(){
        assertEquals("Madison Island", driver.getTitle());

        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click(); //Acount
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a")).click(); //Log in

        assertEquals("Customer Login", driver.getTitle());

        driver.findElement(By.id("email")).sendKeys("jms138@ua.com");
        driver.findElement(By.cssSelector("form#login-form")).submit(); //Login

        assertEquals("This is a required field.",
                driver.findElement(By.xpath("//*[@id=\"advice-required-entry-pass\"]")).getText()
        );

        driver.findElement(By.id("pass")).sendKeys("uappss");
        driver.findElement(By.cssSelector("form#login-form")).submit(); //Login

        assertEquals("My Account",driver.getTitle());
    }

    @Test
    public void loginFailed(){
        assertEquals("Madison Island", driver.getTitle());

        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click(); //Acount
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a")).click(); //Log in

        assertEquals("Customer Login", driver.getTitle());

        driver.findElement(By.id("email")).sendKeys("jms138@ua.com");
        driver.findElement(By.id("pass")).sendKeys("badpasswd");
        driver.findElement(By.cssSelector("form#login-form")).submit(); //Login


        assertEquals("Invalid login or password.",
                driver.findElement(By.className("error-msg")).getText()
        );
    }
}