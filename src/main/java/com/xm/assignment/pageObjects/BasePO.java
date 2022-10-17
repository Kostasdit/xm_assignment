package com.xm.assignment.pageObjects;

import com.xm.assignment.driver.LaunchDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * The base of all page objects
 * All the main actions should be here
 * All the page objects will extend this class
 */

@Component
public class BasePO {

    @Autowired
    protected LaunchDriver launchDriver;



    /**
     * Input a value on a specific locator
     * @param locator
     * @param value
     */
    public void input (String locator , String value){
        waitForElementToBeVisible(locator);
        clear(locator);
        launchDriver.getDriver().findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void clear(String locator){
        launchDriver.getDriver().findElement(By.cssSelector(locator)).clear();
    }

    /**
     * clicks an element
     * @param locator
     */
    public void click (String locator){
        waitForElementToBeVisible(locator);
        launchDriver.getDriver().findElement(By.cssSelector(locator)).click();
    }

    /**
     * select an option from a dropdown
     * @param locator ,option
     */
    public void select (String locator ,String locatorOption){
        waitForElementToBeVisible(locator);
        click(locator);
        click(locatorOption);
    }

    /**
     * Wait for element to be visible
     * @param locator
     */
    public void waitForElementToBeVisible(String locator){
        WebDriverWait wait = new WebDriverWait(launchDriver.getDriver(),Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator))
        ));
    }

    /**
     * check if an element is visible
     * @param locator
     * @return true if it is or false if it is not
     */
    public Boolean isElementVisible(String locator){
        WebElement p= launchDriver.getDriver().findElement(By.cssSelector(locator));
        return p.isDisplayed();
    }

    /**
     * check if the element is present and return true or false by catching the error if false
     * @param locator
     * @return true or flase
     */
    public Boolean isElementPresent(String locator){
        try{
            launchDriver.getDriver().findElement(By.cssSelector(locator));
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

    /**
     * get text of locator
     * @param locator
     * @return
     */
    public String getText (String locator){
        WebElement p= launchDriver.getDriver().findElement(By.cssSelector(locator));
       return p.getText();
    }

    /**
     * move to a specific element
     * @param locator
     */
    public void moveToElement(String locator){
        Actions actions = new Actions(launchDriver.getDriver());
        WebElement element = launchDriver.getDriver().findElement(By.cssSelector(locator));
        actions.moveToElement(element).build().perform();
    }

    /**
     *  Switch frame by name
     */
    public void switchFrameByName(String locator){
        waitForElementToBeVisible(locator);
        launchDriver.getDriver().switchTo().frame(launchDriver.getDriver().findElement(By.cssSelector(locator)));
    }

    /**
     * switch to main frame
     */
    public void switchToMainFrame(){
        launchDriver.getDriver().switchTo().defaultContent();
    }

    /**
     * scroll down
     */
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) launchDriver.getDriver();
        js.executeScript("window.scrollBy(0,250)", "");
    }

    /**
     * Get as list of strings all the similar elements of a specific locator
     * @param locator
     * @return
     */
    public List<String> findAllSimilarLocators (String locator){
        List<WebElement> m =launchDriver.getDriver().findElements(By.cssSelector(locator));
        ArrayList<String> locators = new ArrayList<>();
        for (WebElement webElement : m) {
            locators.add(webElement.getText());
        }
        return locators;
    }

    /**
     *
     * @return the current tab url
     */
    public String getCurrentUrl(){
        return launchDriver.getDriver().getCurrentUrl();
    }

    /**
     * switch tab
     */
    public void switchTab(){
        String originalWindow = launchDriver.getDriver().getWindowHandle();
        for (String windowHandle : launchDriver.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                launchDriver.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    /**
     * Check if element is clickable
     */
    public Boolean isElementClickable(String locator){
        try{
            launchDriver.getDriver().findElement(By.cssSelector(locator)).click();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
