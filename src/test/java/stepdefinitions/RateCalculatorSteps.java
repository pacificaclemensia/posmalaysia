package stepdefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RateCalculatorSteps {
	
	WebDriver driver;
	
	@Given("User navigates to {string}")
    public void user_navigates_to(String url) {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }


	@When("User enters {string} in the {string} postcode field")
    public void user_enters_postcode(String postcode, String field) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement postcodeField = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//*[@id='contentBody']/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[1]/div[3]/div/input")
		));
		postcodeField.click();
		postcodeField.sendKeys(Keys.CONTROL + "a");
		postcodeField.sendKeys(Keys.DELETE);
		postcodeField.sendKeys(postcode);

		
    }
    
	@When("User selects {string} as the {string} country")
    public void user_selects_To_country(String country, String field) {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement toCountryField = wait.until(ExpectedConditions.elementToBeClickable
				 (By.id("mat-input-0")));
		 toCountryField.click();
         toCountryField.clear();
         toCountryField.sendKeys("India");
         List<WebElement> countryOptions = wait.until(
                 ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span"))
             );
         for (WebElement option : countryOptions) {
             if (option.getText().trim().equalsIgnoreCase("India")) {
                 option.click();
                 break;
             }
         }
         System.out.println("India selected successfully!");

    } 
		
	
	
    		
	
	@When("User leaves the {string} postcode field empty")
    public void user_leaves_postcode_empty(String field) {
		WebElement postcodeField = driver.findElement(By.xpath("//*[@id='contentBody']/div/app-static-layout/app-rate-calculator-v2/div/div[3]/div[1]/div[1]/div[3]/div/input"));
		postcodeField.clear();
    }

	@When("User enters {string} in the {string} field")
    public void user_enters_weight(String weight, String field) {
        driver.findElement(By.xpath("//*[@id=\"contentBody\"]/div/app-static-layout/"
        		+ "app-rate-calculator-v2/div/div[3]/div[1]/div[3]/div/div[2]/div/input")).sendKeys(weight);
     
        
	}

	@When("User clicks on Calculate")
    public void user_clicks_calculate() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//*[@id='contentBody']//app-rate-calculator-v2//div[3]/div[2]/a")
		    ));

		    // Scroll into view
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", calculateButton);
	    try {
	        Thread.sleep(1000); // Give time for scrolling effect
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    try {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(calculateButton).click().perform();
	    } catch (Exception e) {
	        System.out.println("Actions click failed, trying JavaScript click...");
	        js.executeScript("arguments[0].click();", calculateButton);
	    }
	}
		
		
	
		
		
    
	
	@Then("User should see multiple shipping quotes and options")
    public void user_verifies_shipping_options() {
		List<WebElement> options = driver.findElements
				(By.cssSelector(".no-underline.cursor-pointer.mt-1.items-center.justify-center.rounded-md.border.bg-\\[\\#ff474f\\].px-12.py-3.text-lg.font-bold.text-white.shadow-sm.focus\\:outline-none"));

        Assert.assertTrue(options.size() > 0); 
    }
	

}
