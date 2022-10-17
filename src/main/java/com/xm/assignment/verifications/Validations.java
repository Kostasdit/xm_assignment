package com.xm.assignment.verifications;

import com.xm.assignment.driver.LaunchDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Validation class
 * Basic validation
 */
@Component
public class Validations {

    @Autowired
    protected LaunchDriver launchDriver;

    /**
     * validation for 2 strings
     * @param a
     * @param b
     */
    public void validateStrings(String a, String b){
        System.out.println("Actual is " + b);
        System.out.println("Expected is " + a);
        Assert.assertEquals(a,b);
    }


    /**
     * validation or at least tried for 2 strings
     * @param a
     * @param b
     */
    public void validateLists(List a, List b){
        System.out.println("Actual is " + b);
        System.out.println("Expected is " + a);
        Assert.assertEquals(a,b);
    }

    /**
     * Validation of the number of the tabs
     * @param number
     */
    public void validateNumberOfTabs(int number) {
        Assert.assertEquals(launchDriver.getDriver().getWindowHandles().size(), number);
    }


    /**
     * validation or at least tried for 2 strings
     * @param a
     * @param b
     */
    public void validateStringExistsToList(List a, String b){
        System.out.println("Sting is " + b);
        boolean isTrue = false;

        for (Object o : a) {
            if (b.equals(o)) {
                isTrue = true;
                break;
            }
        }
        assertTrue(isTrue);
    }

}
