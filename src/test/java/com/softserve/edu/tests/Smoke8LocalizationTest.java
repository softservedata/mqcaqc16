package com.softserve.edu.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.ParameterUtils;
import com.softserve.edu.rs.pages.ATopPage.ChangeLanguageFields;
import com.softserve.edu.rs.pages.Application;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class Smoke8LocalizationTest {

	@DataProvider(parallel = true)
	public Object[][] getApplicationSources(ITestContext context) {
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
		System.out.println("Start TEST checkLocalization2, changeLanguageFields = "
				+ changeLanguageFields.toString());
		// For parallel
		SoftAssert softAssert = new SoftAssert();
		// Precondition
		Application application = Application.get(applicationSources);
		//
		// Steps
		// LoginPage loginPage = new LoginPage(driver);
		LoginPage loginPage = application.load();
		// Steps
		loginPage = loginPage.changeLanguage(changeLanguageFields);
		//Thread.sleep(2000);
		// Check
		Assert.assertEquals(loginPage.getLoginLabelText(),
				LoginPageL10n.LOGIN_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getPasswordLabelText(),
				LoginPageL10n.PASSWORD_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getSignintText(),
				LoginPageL10n.SUBMIT_BUTTON.getLocalization(changeLanguageFields));
		//Thread.sleep(2000);
		// application.logout();
		application.quit();
		softAssert.assertAll();
		System.out.println("Done TEST checkLocalization2, changeLanguageFields = "
				+ changeLanguageFields.toString());
	}

}
