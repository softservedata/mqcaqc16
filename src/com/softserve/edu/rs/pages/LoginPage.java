package com.softserve.edu.rs.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.data.IUser;

public class LoginPage extends ATopPage {
	
	public static enum LoginPageL10n {
        LOGIN_LABEL("Логін","Логин","Login"),
        PASSWORD_LABEL("Пароль","Пароль","Password"),
		SUBMIT_BUTTON("Увійти","Войти","Sign in");
        //
        private HashMap<ChangeLanguageFields, String> field;

        private LoginPageL10n(String... localization) {
        	this.field = new HashMap<ChangeLanguageFields, String>();
        	int i = 0;
        	for (ChangeLanguageFields language : ChangeLanguageFields.values()) {
        		this.field.put(language, localization[i]);
        		i++;
        	}
        }

        public String getLocalization(ChangeLanguageFields language) {
            return this.field.get(language).trim();
        }
    }

	// Fields
    private WebElement loginLabel;
    private WebElement loginInput;
    private WebElement passwordLabel;
    private WebElement passwordInput;
    private WebElement signin;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.loginLabel = driver.findElement(By.xpath("//label[contains(@for,'inputEmail')]"));
		this.loginInput = driver.findElement(By.id("login"));
		this.passwordLabel = driver.findElement(By.xpath("//label[contains(@for,'inputPassword')]"));
		this.passwordInput = driver.findElement(By.id("password"));
		this.signin = driver.findElement(By.cssSelector("button.btn.btn-primary"));
	}

    // PageObject

	// get Data

	public WebElement getLoginLabel() {
		return this.loginLabel;
	}

	public WebElement getLoginInput() {
		return this.loginInput;
	}

	public WebElement getPasswordLabel() {
		return this.passwordLabel;
	}

	public WebElement getPasswordInput() {
		return this.passwordInput;
	}

	public WebElement getSignin() {
		return this.signin;
	}

	// Functional
	
	public String getLoginLabelText() {
		return getLoginLabel().getText().trim();
	}

	public String getLoginInputText() {
		return getLoginInput().getText();
	}

	public String getPasswordLabelText() {
		return getPasswordLabel().getText().trim();
	}

	public String getPasswordInputText() {
		return getPasswordInput().getText();
	}

	public String getSignintText() {
		return getSignin().getText().trim();
	}
	
	// set Data

	public void setLoginInput(String login) {
		getLoginInput().sendKeys(login);
	}

	public void setLoginInputClear(String login) {
		clearLoginInput();
		setLoginInput(login);
	}

	public void setPasswordInput(String password) {
		getPasswordInput().sendKeys(password);
	}

	public void setPasswordInputClear(String password) {
		clearPasswordInput();
		setPasswordInput(password);
	}

	public void clearLoginInput() {
		getLoginInput().clear();
	}

	public void clearPasswordInput() {
		getPasswordInput().clear();
	}

	public void clickLogin() {
		getLoginInput().click();
	}

	public void clickPassword() {
		getPasswordInput().click();
	}

	public void clickSignin() {
		getSignin().click();
	}

    // Business Logic

    public LoginPage changeLanguage(ChangeLanguageFields language) {
    	setChangeLanguage(language);
        // Return a new page object representing the destination.
        return new LoginPage(driver);
    }

    // TODO Develop User class
    private void setLoginData(IUser user) {
    //private void setLoginData(String login, String password) {
		//setLoginInputClear(login);
		//setPasswordInputClear(password);
		setLoginInputClear(user.getLogin());
		setPasswordInputClear(user.getPassword());
		clickSignin();
	}

//    public HomePage successUserLogin(IUser user) {
//        setLoginData(user);
//        // Return a new page object representing the destination.
//        return new HomePage();
//    }

    public AdminHomePage successAdminLogin(IUser admin) {
    //public AdminHomePage successAdminLogin(String login, String password) {
		//setLoginData(login, password);
		setLoginData(admin);
		// Return a new page object representing the destination.
		return new AdminHomePage(driver);
	}

//	public RegistratorHomePage successRegistratorLogin(IUser registrator) {
//		setLoginData(registrator);
//		// Return a new page object representing the destination.
//		return new RegistratorHomePage();
//	}
//
    // TODO Develop User class
    public LoginValidatorPage unsuccessfulLogin(IUser invalidUser) {
	//public LoginValidatorPage unsuccessfulLogin(String login, String password) {
		//setLoginData(login, password);
    	setLoginData(invalidUser);
		return new LoginValidatorPage(driver); // return this;
	}

}
