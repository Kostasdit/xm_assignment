package com.xm.assignment;

import com.xm.assignment.http.SwaggerRestImpl;
import com.xm.assignment.verifications.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

@SpringBootApplication(scanBasePackages = { "com.xm.assignment" })
public class SecondTask extends AbstractTestNGSpringContextTests {

    @Autowired
    protected SwaggerRestImpl swaggerRest;

    @Autowired
    protected Validations validations;

    protected List<String> charUrls,starShips,starShipsClass,pilotUrls,pilotsNames;
    protected String expectedStarShipClass = "Starfighter";
    protected String expectedPilot = "Luke Skywalker";
    protected String url;

    @BeforeClass
    @Parameters({"title","characterName"})
    public void start(String title,String characterName){
        url = swaggerRest.returnFilmUrlOnSpecificTitle(title);
        charUrls = swaggerRest.returnListOfCharacters(url);
        starShips = swaggerRest.returnStarShipUrlsBasedOnCharacterName(charUrls,characterName);
        starShipsClass = swaggerRest.returnStarShipClass(starShips);
        pilotUrls = swaggerRest.returnPilotsUrl(starShips);
        pilotsNames = swaggerRest.returnPilotsNames(pilotUrls);
    }

    @Test(alwaysRun = true, description = "Verify the starship class is Starfighter and Luke Skywalker exists as pilot")
    public void test_1() {
        validations.validateStringExistsToList(starShipsClass,expectedStarShipClass);
        validations.validateStringExistsToList(pilotsNames,expectedPilot);

    }
}
