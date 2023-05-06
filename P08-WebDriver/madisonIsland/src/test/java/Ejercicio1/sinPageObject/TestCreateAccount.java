package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class TestCreateAccount {
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

    @Tag("OnlyOnce")
    @Test
    public void createAccount(){
        assertEquals("Madison Island", driver.getTitle());

        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click(); //Acount
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a")).click(); //Log in

        assertEquals("Customer Login", driver.getTitle());

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click(); //Create an account

        assertEquals("Create New Customer Account", driver.getTitle());

        driver.findElement(By.name("firstname")).sendKeys("jms");
        driver.findElement(By.name("lastname")).sendKeys("138");
        driver.findElement(By.id("email_address")).sendKeys("jms138@ua.com");
        driver.findElement(By.name("password")).sendKeys("uappss");
        driver.findElement(By.cssSelector("button[title='Register']")).click(); //Register

        assertEquals("This is a required field.",
                driver.findElement(By.xpath("//*[@id=\"advice-required-entry-confirmation\"]")).getText()
        );

        driver.findElement(By.id("confirmation")).sendKeys("uappss");
        driver.findElement(By.cssSelector("form#form-validate")).submit(); //Register

        assertEquals("My Account", driver.getTitle());
    }
}