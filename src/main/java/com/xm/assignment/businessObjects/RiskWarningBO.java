package com.xm.assignment.businessObjects;

import com.xm.assignment.pageObjects.RiskWarningPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Business Object of the risk warning Page
 * All the business behind the action in the risk warning page will be here
 */
@Component
public class RiskWarningBO {

    @Autowired
    protected RiskWarningPO riskWarningPO;

    /**
     * Go to the risk disclosure by pressing the risk warning here
     */
    public void goToAndPressRiskWarningHere(){
        riskWarningPO.pressRiskWarningHere();
        riskWarningPO.switchTab();
    }

    /**
     * Get th url of the page
     */
    public String takeUrl(){
        return riskWarningPO.getCurrentUrl();
    }

}
