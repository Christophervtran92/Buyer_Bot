import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./src/Selenium/chromedriver");
        WebElement element;
        long end;
        //Stuff to make selenium actually work
        //solution found at: https://stackoverflow.com/questions/50642308/webdriverexception-unknown-error-devtoolsactiveport-file-doesnt-exist-while-t
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");            // open Browser in maximized mode
        options.addArguments("disable-infobars");           // disabling infobars
        options.addArguments("--disable-extensions");       // disabling extensions
        options.addArguments("--disable-gpu");              // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage");    // overcome limited resource problems
        options.addArguments("--no-sandbox");               // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        //Open page to xbox series x
        //driver.get("https://www.bestbuy.com/site/microsoft-xbox-series-x-1tb-console-black/6428324.p?skuId=6428324");
        driver.get("https://www.bestbuy.com/site/mario-rabbids-kingdom-battle-nintendo-switch/5909500.p?skuId=5909500");

        //Keep refreshing as long as it's out of stock
        while(driver.getPageSource().contains("Add to Cart") == false)
            driver.navigate().refresh();

        //Click the add to cart button
        element = driver.findElement(By.cssSelector(".c-button-primary"));
        element.click();

        while(driver.getPageSource().contains("Subtotal") == false)
        {
            try {driver.wait(1);}
            catch(Exception e) {}
        }

        //Click the cart button
        //element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/div[1]/div/div[3]/div[1]/div/div/div/div/a/span")); //Listing for Xbox Series X, used for testing out of stock
        element = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div/div/div/div/div[1]/div[3]/a")); //Listing for Mario + Rabbids Kingdom Battle, used for testing in stock
        element.click();

        //Check for the "checkout" button on the cart page
        while(driver.getPageSource().contains("Order Summary") == false) {
            try {driver.wait(1);}
            catch(Exception e) {}
        }

        //Click pickup locally button
        element = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[1]/div/div[1]/div[1]/section[1]/div[4]/ul/li/section/div[2]/div[2]/form/div[3]/fieldset/div[1]/div[1]/div/div/span/input"));
        element.click();

        //Click the checkout button
        element = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[1]/div/div[1]/div[1]/section[2]/div/div/div[4]/div/div[1]/button"));
        element.click();

        //Check for the "continue as guest" button
        while(driver.getPageSource().contains("Continue as Guest") == false){}

        //Click the continue as guest button
        element = driver.findElement(By.cssSelector(".guest"));
        element.click();

        while(driver.getPageSource().contains("Contact Information") == false){}

        //Input email
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[2]/form/section/div/div[2]/div/section/div[2]/label/div/input"));
        element.sendKeys("email@email.com");

        //Input phone #
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[2]/form/section/div/div[2]/div/section/div[3]/label/div/input"));
        element.sendKeys("4087854578");

        //Click continue to payment info
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[2]/form/section/div/div[2]/div/div/button"));
        element.click();

        while(driver.getPageSource().contains("Credit or Debit Card") == false){}

        //Input CC info
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/div[1]/div/section/div[1]/div/input"));
        element.sendKeys("1111222233334444");

        //Input first name
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[2]/div/label/input"));
        element.sendKeys("John");

        //Input last name
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[3]/div/label/input"));
        element.sendKeys("Doe");

        //Input Address
        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[4]/div[2]/label/div[2]/div/input"));
        element.sendKeys("123 Fakestreet");

        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[6]/div[1]/div/label/input"));
        element.sendKeys("San Jose");

        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[6]/div[2]/div/label/div[2]/select"));
        Select ddElement = new Select(element);
        ddElement.selectByValue("CA");

        element = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div[1]/main/div[2]/div[3]/div/section/div[1]/form/div/form/div[7]/div/div[1]/div/label/input"));
        element.sendKeys("12345");



        end = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < end) {
        }
        driver.quit();
    }
}
