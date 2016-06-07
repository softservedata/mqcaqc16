package com.softserve.edu.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.rs.pages.ATopPage.ChangeLanguageFields;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageL10n;

public class LocalizationLoginPageTest {

    @DataProvider(parallel = true)
    public Object[][] getApplicationSources() {
        return new Object[][] {
                { ChangeLanguageFields.ENGLISH },
                { ChangeLanguageFields.RUSSIAN },
                { ChangeLanguageFields.UKRAINIAN },
                };
    }

	@Test(dataProvider = "getApplicationSources")
	public void checkChangeLanguage(ChangeLanguageFields changeLanguageFields) throws Exception {
		// Precondition
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://registrator.herokuapp.com/login");
		// 
		LoginPage loginPage = new LoginPage(driver);
		// Steps
		loginPage = loginPage.changeLanguage(changeLanguageFields);
		Thread.sleep(5000);
		// Check
		Assert.assertEquals(loginPage.getLoginLabelText(), LoginPageL10n.LOGIN_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getPasswordLabelText(), LoginPageL10n.PASSWORD_LABEL.getLocalization(changeLanguageFields));
		Assert.assertEquals(loginPage.getSignintText(), LoginPageL10n.SUBMIT_BUTTON.getLocalization(changeLanguageFields));
		Thread.sleep(5000);
		driver.quit();
	}

}
