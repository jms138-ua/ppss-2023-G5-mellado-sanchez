package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductComparisonPage{
    private WebDriver driver;
    String myHandleId;
    String parentHandleId;

    @FindBy(css = "Button[title='Close Window']")
    WebElement webtn_close;

    public ProductComparisonPage(WebDriver driver){
        this.driver = driver;
    }

    public ShoesPage close(){
        webtn_close.click();
        driver.switchTo().window(parentHandleId);
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}