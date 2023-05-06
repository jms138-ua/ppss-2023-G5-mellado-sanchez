package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;

import java.util.Set;

public class ShoesPage{
    private WebDriver driver;
    private String myHandleId;

    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]/div/div[2]/ul/li[2]/a")
    WebElement wel_wingtipShoe;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]/div/div[2]/ul/li[2]/a")
    WebElement wel_suedeShoe;
    @FindBy(css = "button[title='Compare']")
    WebElement webtn_compare;
    @FindBy(linkText = "Clear All")
    WebElement wel_clearCompare;
    @FindBy(className = "success-msg")
    WebElement wes_status;

    public ShoesPage(WebDriver driver){
        this.driver = driver;
        myHandleId = driver.getWindowHandle();
    }

    public void selectShoeToCompare(int number){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if(number==5){
            jse.executeScript("arguments[0].scrollIntoView();", wel_wingtipShoe);
            wel_wingtipShoe.click();
        }else if (number==6){
            jse.executeScript("arguments[0].scrollIntoView();", wel_suedeShoe);
            wel_suedeShoe.click();
        }
    }

    public ProductComparisonPage submitCompare(){
        webtn_compare.click();
        ProductComparisonPage productComparisonPage = PageFactory.initElements(driver, ProductComparisonPage.class);

        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        productComparisonPage.myHandleId = handleIds[0];
        productComparisonPage.parentHandleId = handleIds[1];

        driver.switchTo().window(productComparisonPage.myHandleId);
        return productComparisonPage;
    }

    public String ClearComparison(){
        wel_clearCompare.click();

        Alert alert = driver.switchTo().alert();
        String alert_msg = alert.getText();
        alert.accept();
        return alert_msg;
    }

    public String getResultStatus(){
        return wes_status.getText();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}