package com.xm.assignment;

import com.xm.assignment.businessObjects.EconomicCalendarBO;
import com.xm.assignment.businessObjects.HomeBO;
import com.xm.assignment.businessObjects.RiskWarningBO;
import com.xm.assignment.driver.LaunchDriver;
import com.xm.assignment.utilities.Date;
import com.xm.assignment.verifications.Validations;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@SpringBootApplication(scanBasePackages = { "com.xm.assignment" })
public class FirstTask extends AbstractTestNGSpringContextTests {

	@Autowired
	protected HomeBO homeBO;

	@Autowired
	protected EconomicCalendarBO economicCalendarBO;
	
	@Autowired
	protected RiskWarningBO riskWarningBO;

	@Autowired
	protected Validations validations;

	@Autowired
	protected Date date;

	@Autowired
	protected LaunchDriver launchDriver;

	protected String validationUrl = "https://www.xm.com/assets/pdf/new/docs/XM-Risk-Disclosures-for-Financial-Instruments.pdf?v5";


	@BeforeClass
	@Parameters({"targetHostUrl","driverType","resolution"})
	public void openBrowser(String targetHostUrl, String driverType,String resolution){
		LaunchDriver.launchBrowser(targetHostUrl,driverType,resolution);

	}

	@BeforeClass(dependsOnMethods = "openBrowser")
	public void loadData(){
		homeBO.goToEconomicCalendarPage();
	}


	@Test(alwaysRun = true, description = "Verify the Dates of the Economic Calendar Page regarding the action(Today,tomorrow,Yesterday,this week)")
	public void test_1() {
		validations.validateStrings(date.getYesterdayDate(),economicCalendarBO.takeYesterdayDate());
		validations.validateStrings(date.getTodayDate(),economicCalendarBO.takeTodayDate());
		validations.validateStrings(date.getTomorrowDate(),economicCalendarBO.takeTomorrowDate());
		validations.validateLists(date.getWeekDate(),economicCalendarBO.takeThisWeekDates());
	}

	@Test(alwaysRun = true, description = "Verify the End to End Process",dependsOnMethods = "test_1" )
	public void test_2() {
		goToRiskWarningPageAndOpenRiskDisclosure();
		String url = riskWarningBO.takeUrl();
		validations.validateNumberOfTabs(2); // two tabs
		validations.validateStrings(url,validationUrl);

	}

	@AfterClass
	public void endTest(){
		launchDriver.closeDriver();
	}


	/**
	 * Go to risk warning page and to risk disclosure page by pressing the "here"
	 */
	public void goToRiskWarningPageAndOpenRiskDisclosure(){
		economicCalendarBO.goToDisclaimerHereUrl();
		riskWarningBO.goToAndPressRiskWarningHere();
	}


}
