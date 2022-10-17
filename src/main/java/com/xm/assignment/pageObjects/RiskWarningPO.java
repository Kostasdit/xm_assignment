package com.xm.assignment.pageObjects;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;


/**
 * Risk warning Page -Page Object
 * All the locators and the relevant actions in the page will be here
 */
@Component
public class RiskWarningPO extends BasePO{

    enum RiskWaringLocators{
        RISK_WARNING_HERE("a[href='/assets/pdf/new/docs/XM-Risk-Disclosures-for-Financial-Instruments.pdf?v5']"),
        ;

        private final String myLocator;

        RiskWaringLocators(String locator){
            myLocator = locator;
        }

        String getLocator() {return myLocator;}

        String getWithParams(Object... params){
            return MessageFormat.format(getLocator(),params);
        }


    }

    /**
     * Go and press the risk wanring link
     */
    public void pressRiskWarningHere(){
        moveToElement(RiskWaringLocators.RISK_WARNING_HERE.getLocator());
        scrollDown();
        click(RiskWaringLocators.RISK_WARNING_HERE.getLocator());
    }
}
