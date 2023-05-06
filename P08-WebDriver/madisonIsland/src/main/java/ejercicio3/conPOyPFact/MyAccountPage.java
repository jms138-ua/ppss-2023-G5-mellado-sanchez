package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

public class MyAccountPage{
    private WebDriver driver;

    @FindBy(linkText = "Accessories")
    WebElement wel_accesories;
    @FindBy(linkText = "Shoes")
    WebElement wel_shoes;

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
    }

    public ShoesPage accessShoesPage(){
        Actions builder = new Actions(driver);
        builder.moveToElement(wel_accesories);
        builder.perform();
        wel_shoes.click();

        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}