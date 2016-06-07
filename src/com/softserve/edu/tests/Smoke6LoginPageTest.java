package com.softserve.edu.tests;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.IUser;
import com.softserve.edu.data.ParameterUtils;
import com.softserve.edu.data.UserRepository;
import com.softserve.edu.rs.pages.AdminHomePage;
import com.softserve.edu.rs.pages.Application;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class Smoke6LoginPageTest {

	@DataProvider//(parallel = true)
    public Object[][] getApplicationSources(ITestContext context) {
//		// Suite
//        HashMap<String, String> suiteParameters = new HashMap<String, String>(context.getCurrentXmlTest().getSuite().getParameters()); 
//        for (String key : suiteParameters.keySet()) {
//        	System.out.println("Suite parameter: key=" + key + " value=" + suiteParameters.get(key));
//        }
//        // Test
//        //HashMap<String, String> testParameters = new HashMap<String, String>(context.getCurrentXmlTest().getTestParameters()); 
//        HashMap<String, String> testParameters = new HashMap<String, String>(context.getCurrentXmlTest().getAllParameters());
//        for (String key : testParameters.keySet()) {
//        	System.out.println("Test parameter: key=" + key + " value=" + testParameters.get(key));
//        }
        return new Object[][] {
        		{ ParameterUtils.get().updateApplicationSources(
        					ApplicationSourcesRepository.getFirefoxHerokuApplication(), context),
        			UserRepository.get().getAdmin() },
                };
    }

    @Test(dataProvider = "getApplicationSources")
	public void checkAdminLogin2(ApplicationSources applicationSources, IUser adminUser) throws Exception {
    	// For parallel
    	SoftAssert softAssert = new SoftAssert();
		// Precondition
    	Application application = Application.get(applicationSources);
    	//
		// Steps
		//LoginPage loginPage = new LoginPage(driver);
    	LoginPage loginPage = application.load();
		AdminHomePage adminHomePage = loginPage
				//.successAdminLogin(adminUser.getLogin(), adminUser.getPassword()); 
				.successAdminLogin(adminUser);
		//
		// Check
		softAssert.assertEquals(adminHomePage.getLoginAccountText(), adminUser.getLogin());
		Thread.sleep(1000);
		//
		// Return to previous state
		loginPage = adminHomePage.logout();
		softAssert.assertEquals(loginPage.getLoginLabelText(),
				LoginPageL10n.LOGIN_LABEL.getLocalization(loginPage.getSelectedLanguage()));
		//
		Thread.sleep(1000);
		//application.logout();
		application.quit();
		softAssert.assertAll();
	}

}
