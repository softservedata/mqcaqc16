package com.softserve.edu.tests;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.ParameterUtils;
import com.softserve.edu.rs.pages.ATopPage.ChangeLanguageFields;
import com.softserve.edu.rs.pages.Application;
import com.softserve.edu.rs.pages.Application.ReporterLevels;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class Smoke8LocalizationTest {
	private static final Logger logger = LoggerFactory.getLogger(Smoke8LocalizationTest.class);
	private HashMap<Long, Boolean> isTestCompleted;
	private HashMap<Long, Application> applications;

	private Boolean getCurrentTestCompleted() {
		return this.isTestCompleted.get(Thread.currentThread().getId());
	}
	
	private void setCurrentTestCompleted(Boolean currentTestCompleted) {
		this.isTestCompleted.put(Thread.currentThread().getId(), currentTestCompleted);
	}

	private Application getApplication() {
		return this.applications.get(Thread.currentThread().getId());
	}
	
	private void setApplication(Application currentApplication) {
		this.applications.put(Thread.currentThread().getId(), currentApplication);
	}

	private void screenShortToReport() {
		String insertHTMLCode = "<br><div><image style=\"max-width:100%%\" src=\"%s\"/></div>";
		String fileNamePath = getApplication().captureScreen();
		Reporter.log(String.format(insertHTMLCode,fileNamePath));
	                //fileNamePath.substring(fileNamePath.lastIndexOf("/") + 1)));
	}

	@BeforeClass
	public void oneTimeSetUp() {
		isTestCompleted = new HashMap<Long, Boolean>();
		applications = new HashMap<Long, Application> ();
		logger.info("+++@BeforeClass Thread ID = " + Thread.currentThread().getId());
	}
	
	@BeforeMethod
	public void setUp() {
		logger.info("+++@BeforeMethod Thread ID = " + Thread.currentThread().getId());
		setCurrentTestCompleted(false);
	}

	@AfterMethod
	public void tearDown() {
		logger.info("+++@AfterMethod Thread ID = " + Thread.currentThread().getId());
		if  ((getCurrentTestCompleted() != null)
				&& (!getCurrentTestCompleted())) {
			logger.error("TC Crashed");
			if (getApplication() != null)  {
				logger.error("***** ScreenShortPath = " + getApplication().captureScreen());
			}
		}
		if (getApplication() != null) {
			getApplication().quit();
		}
	}

	@DataProvider(parallel = true)
	public Object[][] getApplicationSources(ITestContext context) {
		logger.info("+++@DataProvider Thread ID = " + Thread.currentThread().getId());
		logger.debug("Start DataProvider");
//		String browserName = System.getProperty("browser.name");
//		System.out.println("browserName = " + browserName);
		return new Object[][] {
				{ ParameterUtils.get().updateApplicationSources(
						ApplicationSourcesRepository.getFirefoxHerokuApplication(),
								context),  ChangeLanguageFields.ENGLISH },
				{ ParameterUtils.get().updateApplicationSources(
						ApplicationSourcesRepository.getFirefoxHerokuApplication(),
								context),  ChangeLanguageFields.RUSSIAN },
				{ ParameterUtils.get().updateApplicationSources(
						ApplicationSourcesRepository.getFirefoxHerokuApplication(),
								context),  ChangeLanguageFields.UKRAINIAN },
				};
//		return new Object[][] {
//				{ ApplicationSourcesRepository.getHtmlUnitHerokuApplication(), 
//					ChangeLanguageFields.ENGLISH },
//				{ ApplicationSourcesRepository.getHtmlUnitHerokuApplication(),
//						ChangeLanguageFields.RUSSIAN },
//				{ ApplicationSourcesRepository.getHtmlUnitHerokuApplication(),
//							ChangeLanguageFields.UKRAINIAN },
//				};
	}

	@Test(dataProvider = "getApplicationSources")
	public void checkLocalization2(ApplicationSources applicationSources,
			ChangeLanguageFields changeLanguageFields) throws Exception {
		Reporter.log("<BR>###@Test Start Thread ID = " + String.valueOf(Thread.currentThread().getId()),
				ReporterLevels.INFO_LEVEL.getLevel(), true);
		logger.info("Start TC");
		logger.info("+++@Test Thread ID = " + Thread.currentThread().getId());
		logger.debug("Start TEST checkLocalization2, changeLanguageFields = "
				+ changeLanguageFields.toString());
//		System.out.println("Start TEST checkLocalization2, changeLanguageFields = "
//				+ changeLanguageFields.toString());
		// For parallel
		//SoftAssert softAssert = new SoftAssert();
		// Precondition
		Application application = Application.get(applicationSources);
		setApplication(application);
		//
		// Steps
		// LoginPage loginPage = new LoginPage(driver);
		LoginPage loginPage = application.load();
		// Steps
		loginPage = loginPage.changeLanguage(changeLanguageFields);
		//Thread.sleep(2000);
		// Check
		screenShortToReport();
		logger.info("Assert.assertEquals Actual: " + loginPage.getLoginLabelText()
				+ " Expected: " + LoginPageL10n.LOGIN_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getLoginLabelText(),
				LoginPageL10n.LOGIN_LABEL.getLocalization(changeLanguageFields));
		logger.info("Assert.assertEquals Actual: " + loginPage.getPasswordLabelText()
			+ " Expected: " + LoginPageL10n.PASSWORD_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getPasswordLabelText(),
				LoginPageL10n.PASSWORD_LABEL.getLocalization(changeLanguageFields));
		logger.info("Assert.assertEquals Actual: " + loginPage.getSignintText()
			+ " Expected: " + LoginPageL10n.SUBMIT_BUTTON.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getSignintText(),
				LoginPageL10n.SUBMIT_BUTTON.getLocalization(changeLanguageFields));
		//Thread.sleep(2000);
		// application.logout();
		//application.quit();
		//softAssert.assertAll();
		logger.debug("Done TEST checkLocalization2, changeLanguageFields = "
				+ changeLanguageFields.toString());
//		System.out.println("Done TEST checkLocalization2, changeLanguageFields = "
//				+ changeLanguageFields.toString());
		logger.info("Done TC");
		setCurrentTestCompleted(true);
		Reporter.log("<BR>###@Test Done Thread ID = " + String.valueOf(Thread.currentThread().getId()),
				ReporterLevels.INFO_LEVEL.getLevel(), true);
		Thread.sleep(1000);
	}

}
