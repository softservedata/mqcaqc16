package com.softserve.edu.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.rs.pages.ATopPage.ChangeLanguageFields;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginValidatorPage;

public class SmokeLoginPageTest {

	@Test
	public void checkChangeLanguage() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://registrator.herokuapp.com/login");
		//
		LoginPage loginPage = new LoginPage(driver);
		// Check Ukrainian
		Assert.assertEquals(loginPage.getLoginLabelText(), "Логін");
		Assert.assertEquals(loginPage.getPasswordLabelText(), "Пароль");
		Assert.assertEquals(loginPage.getSignintText(), "Увійти");
		Thread.sleep(2000);
		// Switch to Russian
		loginPage = loginPage.changeLanguage(ChangeLanguageFields.RUSSIAN);
		// Check Russian
		Assert.assertEquals(loginPage.getLoginLabelText(), "Логин");
		Assert.assertEquals(loginPage.getPasswordLabelText(), "Пароль");
		Assert.assertEquals(loginPage.getSignintText(), "Войти");
		Thread.sleep(2000);
		// Switch to English
		loginPage = loginPage.changeLanguage(ChangeLanguageFields.ENGLISH);
		// Check Russian
		Assert.assertEquals(loginPage.getLoginLabelText(), "Login");
		Assert.assertEquals(loginPage.getPasswordLabelText(), "Password");
		Assert.assertEquals(loginPage.getSignintText(), "Sign in");
		Thread.sleep(2000);
		// Switch to Ukrainian
		loginPage = loginPage.changeLanguage(ChangeLanguageFields.UKRAINIAN);
		// Check Russian
		Assert.assertEquals(loginPage.getLoginLabelText(), "Логін");
		Assert.assertEquals(loginPage.getPasswordLabelText(), "Пароль");
		Assert.assertEquals(loginPage.getSignintText(), "Увійти");
		Thread.sleep(2000);
		driver.quit();
	}

	//@Test
	public void checkInvalidLogin() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://registrator.herokuapp.com/login");
		//
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getChangeLanguageSelectedText(),
				ChangeLanguageFields.UKRAINIAN.toString());
		// Switch to Ukrainian
		loginPage = loginPage.changeLanguage(ChangeLanguageFields.UKRAINIAN);
		//LoginValidatorPage loginValidatorPage = loginPage.unsuccessfulLogin("proba", "proba");
		// STUB
		LoginValidatorPage loginValidatorPage = new LoginValidatorPage(driver);
		Assert.assertEquals(loginValidatorPage.getInvalidLoginValidatorText(), "Неправильний логін або пароль");
		Thread.sleep(2000);
		driver.quit();
	}

}
