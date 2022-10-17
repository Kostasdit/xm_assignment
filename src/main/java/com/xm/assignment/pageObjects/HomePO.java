package com.xm.assignment.pageObjects;

import org.springframework.stereotype.Component;
import java.text.MessageFormat;

/**
 * Home Page Object
 * All the actions and the locators for the Home Page will be here
 */
@Component
public class HomePO extends BasePO{

    enum LandingPageLocator{
        POP_UP_ACCEPT_ALL("button.gtm-acceptDefaultCookieFirstVisit"),
        MENU("button.toggleLeftNav"),
        RESEARCH_AND_EDUCATION_BUTTON("li.main_nav_research"),
        RESEARCH_AND_EDUCATION_FROM_MENU("a[aria-controls='researchMenu']"),
        ECONOMIC_CALENDAR_BUTTON("a[href = 'https://www.xm.com/research/economicCalendar']"),
        ECONOMIC_CALENDAR_BUTTON_800_RESOLUTION("a[href = 'https://www.xm.com/research/economicCalendar'] i")
        ;

        private final String myLocator;

        LandingPageLocator(String locator){
            myLocator = locator;
        }

        String getLocator() {return myLocator;}

        String getWithParams(Object... params){
            return MessageFormat.format(getLocator(),params);
        }


    }

    /**
     * Close the pop up by accepting all if it is visible
     */
    public void closePopUpAcceptAll(){
        if(isElementVisible(LandingPageLocator.POP_UP_ACCEPT_ALL.getLocator())) {
            click(LandingPageLocator.POP_UP_ACCEPT_ALL.getLocator());
        }
    }

    /**
     * click the education and research button regarding the resolution
     * max -> click
     * other -> open menu and click
     */
    public void clickResearchAndEducation(){
        if(isElementVisible(LandingPageLocator.RESEARCH_AND_EDUCATION_BUTTON.getLocator())){
            click(LandingPageLocator.RESEARCH_AND_EDUCATION_BUTTON.getLocator());
        }else{
            click(LandingPageLocator.MENU.getLocator());
            click(LandingPageLocator.RESEARCH_AND_EDUCATION_FROM_MENU.getLocator());
        }
    }

    /**
     * click the economic calendar button
     */
    public void clickEconomicCalendar(){
        scrollDown();
        if(isElementVisible(LandingPageLocator.ECONOMIC_CALENDAR_BUTTON.getLocator())) {
            click(LandingPageLocator.ECONOMIC_CALENDAR_BUTTON.getLocator());
        }else {
            click(LandingPageLocator.ECONOMIC_CALENDAR_BUTTON_800_RESOLUTION.getLocator());
        }
    }



}
