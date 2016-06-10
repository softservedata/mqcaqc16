package com.softserve.edu.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.IUser;
import com.softserve.edu.data.UserRepository;
import com.softserve.edu.rs.pages.AdminHomePage;
import com.softserve.edu.rs.pages.Application;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class Smoke3LoginPageTest {

    @DataProvider//(parallel = true)
    public Object[][] getApplicationSources() {
        return new Object[][] {
        		{ ApplicationSourcesRepository.getFirefoxHerokuApplication(),
        			UserRepository.get().getAdmin() },
//        		{ ApplicationSourcesRepository.getChromeHerokuApplication(),
//            			UserRepository.get().getAdmin() },
                };
    }

    @Test(dataProvider = "getApplicationSources")
	public void checkAdminLogin2(ApplicationSources applicationSources, IUser adminUser) throws Exception {
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
		Assert.assertEquals(adminHomePage.getLoginAccountText(), adminUser.getLogin());
		Thread.sleep(5000);
		//
		// Return to previous state
		loginPage = adminHomePage.logout();
		Assert.assertEquals(loginPage.getLoginLabelText(),
				LoginPageL10n.LOGIN_LABEL.getLocalization(loginPage.getSelectedLanguage()));
		//
		Thread.sleep(5000);
		//application.logout();
		application.quit();
	}

}
