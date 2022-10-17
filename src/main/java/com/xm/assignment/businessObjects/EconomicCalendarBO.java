package com.xm.assignment.businessObjects;

import com.xm.assignment.pageObjects.EconomicCalendarPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Business Object of the economic calendar Page
 * All the business behind the action in the Economic calendar page will be here
 */
@Component
public class EconomicCalendarBO {

    @Autowired
    protected EconomicCalendarPO economicCalendarPO;

    /**
     * switch to main frame and move and to disclaimer here button
     */
    public void goToDisclaimerHereUrl(){
        goToMainFrame();
        economicCalendarPO.moveAndPressDisclaimerHere();
    }

    /**
     * switch to calendar frame
     */
    public void goToTheCalendarFrame(){
        economicCalendarPO.switchToCalendarFrame();
    }

    /**
     * switch to main frame
     */
    public void goToMainFrame(){
        economicCalendarPO.switchToMainFrame();
    }

    /**
     * take Yesterday date
     * @return yesterday date
     */
    public String takeYesterdayDate() {
        goToTheCalendarFrame();
        economicCalendarPO.pressYesterdayButton();
        return economicCalendarPO.getDateFromEvents();
    }

    /**
     * take Today date
     * @return today date
     */
    public String takeTodayDate() {
        goToTheCalendarFrame();
        economicCalendarPO.pressTodayButton();
        return economicCalendarPO.getDateFromEvents();
    }

    /**
     * take Tomorrow date
     * @return tomorrow date
     */
    public String takeTomorrowDate() {
        goToTheCalendarFrame();
        economicCalendarPO.pressTomorrowButton();
        return economicCalendarPO.getDateFromEvents();
    }

    /**
     * take Week dates
     * @return a list of the dates of the week
     */
    public List<String> takeThisWeekDates() {
        goToTheCalendarFrame();
        economicCalendarPO.pressThisWeekButton();
        return economicCalendarPO.returnWeekDates();
    }

}
