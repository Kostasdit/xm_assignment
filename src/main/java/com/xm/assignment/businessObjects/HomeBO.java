package com.xm.assignment.businessObjects;


import com.xm.assignment.pageObjects.HomePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Home BO is the business Object for actions business related on the home page
 */
@Component
public class HomeBO {

    @Autowired
    protected HomePO homePO;


    /**
     * Process to go to the EconomicCalendar  Page
     */
    public void goToEconomicCalendarPage() {
      closePopUP();
      pressResearchAndEducation();
      pressEconomicCalendar();
    }

    /**
     * Close Pop Up
     */
    public void closePopUP(){
        homePO.closePopUpAcceptAll();
    }

    /**
     * Press the research and education option
     */
    public void pressResearchAndEducation(){
        homePO.clickResearchAndEducation();
    }

    /**
     * press the economic calendar option
     */
    public void pressEconomicCalendar(){
        homePO.clickEconomicCalendar();
    }


}
