package com.softserve.edu.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.data.ApplicationSources;
import com.softserve.edu.data.ApplicationSourcesRepository;
import com.softserve.edu.data.IUser;
import com.softserve.edu.data.UserRepository;
import com.softserve.edu.rs.pages.LogPage;

public class Smoke5LoginPageTest {

	@DataProvider // (parallel = true)
	public Object[][] getApplicationSources() {
		return new Object[][] {
				{ ApplicationSourcesRepository.getFirefoxHerokuApplication(), UserRepository.get().getAdmin() },
				// { ApplicationSourcesRepository.getChromeHerokuApplication(),
				// UserRepository.get().getAdmin() },
		};
	}

	@Test(dataProvider = "getApplicationSources")
	public void checkAdminLogin2(ApplicationSources applicationSources, IUser adminUser) throws Exception {
		// Precondition
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(applicationSources.getImplicitTimeOut(), TimeUnit.SECONDS);
		driver.get(applicationSources.getLoginUrl());
		//
		// Steps
		LogPage logPage = new LogPage(driver);
		//LogPage logPage = PageFactory.initElements(driver, LogPage.class);
		//
		logPage.getLoginInput().sendKeys("***** " + adminUser.getLogin()+" *****");
		logPage.getPasswordInput().sendKeys(adminUser.getPassword());
		Thread.sleep(2000);
		driver.navigate().refresh();
		//
		logPage.getLoginInput().sendKeys(adminUser.getLogin());
		logPage.getPasswordInput().sendKeys(adminUser.getPassword());
		Thread.sleep(2000);
		logPage.getSignin().click();
		//
		Thread.sleep(2000);
		driver.quit();
	}

}
