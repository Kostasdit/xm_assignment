package com.xm.assignment.pageObjects;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * Economic Calendar Page -Page Object
 * All the locators and the relevant actions in the page will be here
 */
@Component
public class EconomicCalendarPO extends BasePO {

    enum EconomicCalendarPageLocator{
        CALENDAR_BUTTON_YESTERDAY("a[id='timeFrame_yesterday']"),
        CALENDAR_BUTTON_TODAY("a[id='timeFrame_today']"),
        CALENDAR_BUTTON_TOMORROW("a[id='timeFrame_tomorrow']"),
        CALENDAR_BUTTON_THIS_WEEK("a[id='timeFrame_thisWeek']"),
        CALENDAR_FRAME("iframe[title='economicCalendar']"),
        EVENTS_TABLE("table[id='ecEventsTable']"),
        EVENTS_DATE("table td[id*='theDay']"),
        DISCLAIMER_HERE("p a[href='/research/risk_warning']"),
        CALENDAR_SMALL_RESOLUTION_MENU("li.saveSpace"),
        ;

        private final String myLocator;

        EconomicCalendarPageLocator(String locator){
            myLocator = locator;
        }

        String getLocator() {return myLocator;}

        String getWithParams(Object... params){
            return MessageFormat.format(getLocator(),params);
        }


    }

    /**
     * Press the Yesterday button and wait for events to load
     */
    public void pressYesterdayButton(){
        if(isElementVisible(EconomicCalendarPageLocator.CALENDAR_BUTTON_YESTERDAY.getLocator())) {
            click(EconomicCalendarPageLocator.CALENDAR_BUTTON_YESTERDAY.getLocator());
        }else {
            select(EconomicCalendarPageLocator.CALENDAR_SMALL_RESOLUTION_MENU.getLocator(),EconomicCalendarPageLocator.CALENDAR_BUTTON_YESTERDAY.getLocator());
        }
        waitForTableEvents();

    }

    /**
     * Press the Today button and wait for events to load
     */
    public void pressTodayButton(){
        if(isElementVisible(EconomicCalendarPageLocator.CALENDAR_BUTTON_TODAY.getLocator())) {
            click(EconomicCalendarPageLocator.CALENDAR_BUTTON_TODAY.getLocator());
        }else {
                select(EconomicCalendarPageLocator.CALENDAR_SMALL_RESOLUTION_MENU.getLocator(),EconomicCalendarPageLocator.CALENDAR_BUTTON_TODAY.getLocator());
        }
        waitForTableEvents();
    }

    /**
     * Press the Tomorrow button and wait for events to load
     */
    public void pressTomorrowButton(){
        if(isElementVisible(EconomicCalendarPageLocator.CALENDAR_BUTTON_TOMORROW.getLocator())) {
            click(EconomicCalendarPageLocator.CALENDAR_BUTTON_TOMORROW.getLocator());
        }else {
            select(EconomicCalendarPageLocator.CALENDAR_SMALL_RESOLUTION_MENU.getLocator(),EconomicCalendarPageLocator.CALENDAR_BUTTON_TOMORROW.getLocator());
        }
        waitForTableEvents();
    }

    /**
     * Press this week button and wait for events to load
     */
    public void pressThisWeekButton(){
        if(isElementVisible(EconomicCalendarPageLocator.CALENDAR_BUTTON_THIS_WEEK.getLocator())) {
            click(EconomicCalendarPageLocator.CALENDAR_BUTTON_THIS_WEEK.getLocator());
        }else {
            select(EconomicCalendarPageLocator.CALENDAR_SMALL_RESOLUTION_MENU.getLocator(),EconomicCalendarPageLocator.CALENDAR_BUTTON_THIS_WEEK.getLocator());
        }
        waitForTableEvents();
    }

    /**
     * Get the date of the events
     * @return the date
     */
    public String getDateFromEvents(){
        return getText(EconomicCalendarPageLocator.EVENTS_DATE.getLocator());
    }

    /**
     * Go to the calendar frame if you are not already
     */
    public void switchToCalendarFrame(){
        if(isElementPresent(EconomicCalendarPageLocator.CALENDAR_FRAME.getLocator())) {
            scrollDown();
            switchFrameByName(EconomicCalendarPageLocator.CALENDAR_FRAME.getLocator());
        }
    }


    /**
     * Wait for the events to be loaded after selected a time period
     */
    public void waitForTableEvents(){
        waitForElementToBeVisible(EconomicCalendarPageLocator.EVENTS_TABLE.getLocator());
    }

    /**
     * Go to the disclaimer here link and press it
     */
    public void moveAndPressDisclaimerHere(){
        moveToElement(EconomicCalendarPageLocator.DISCLAIMER_HERE.getLocator());
        scrollDown();
        click(EconomicCalendarPageLocator.DISCLAIMER_HERE.getLocator());
    }

    /**
     * Take all the dates of the week and return them
     * @return the dates of the week as string
     */
    public List<String> returnWeekDates(){
        return findAllSimilarLocators(EconomicCalendarPageLocator.EVENTS_DATE.getLocator());
    }
}
