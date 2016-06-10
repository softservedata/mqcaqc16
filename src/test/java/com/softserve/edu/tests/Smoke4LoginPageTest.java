package com.softserve.edu.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.IUser;
import com.softserve.edu.data.UserRepository;
import com.softserve.edu.rs.pages.AdminHomePage;
import com.softserve.edu.rs.pages.Application;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class Smoke4LoginPageTest {
//	SoftAssert softAssert;
//
//	@BeforeClass
//	public void oneTimeSetUp() {
//		softAssert = new SoftAssert();
//	}

    @DataProvider(parallel = true)
    public Object[][] getApplicationSources() {
        return new Object[][] {
        		{ ApplicationSourcesRepository.getFirefoxHerokuApplication(),
        			UserRepository.get().getAdmin() },
        		{ ApplicationSourcesRepository.getChromeHerokuApplication(),
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
		System.out.println("Assert_1 Start");
		softAssert.assertEquals(adminHomePage.getLoginAccountText(), adminUser.getLogin()+"1");
		System.out.println("Assert_1 Done");
		Thread.sleep(1000);
		//
		// Return to previous state
		loginPage = adminHomePage.logout();
		System.out.println("Assert_2 Start");
		softAssert.assertEquals(loginPage.getLoginLabelText(),
				LoginPageL10n.LOGIN_LABEL.getLocalization(loginPage.getSelectedLanguage()));
		System.out.println("Assert_2 Done");
		//
		Thread.sleep(1000);
		//application.logout();
		application.quit();
		softAssert.assertAll();
	}

}
