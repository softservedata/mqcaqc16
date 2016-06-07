package com.softserve.edu.rs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ACommonPage extends ATopPage {

	// Fields
	private WebElement loginAccount;
	private WebElement menuAccount;
	//
	private WebElement changePassword;
	private WebElement resetPassword;
	private WebElement logout;

	public ACommonPage(WebDriver driver) {
		super(driver);
		this.loginAccount = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
		this.menuAccount = driver.findElement(By.cssSelector(".btn.btn-primary.btn-sm.dropdown-toggle"));
	}

	// PageObject

	// get Data

	public WebElement getLoginAccount() {
		return this.loginAccount;
	}

	public WebElement getMenuAccount() {
		return this.menuAccount;
	}

	public WebElement getChangePassword() {
		clickMenuAccount();
		return this.changePassword;
	}

	public WebElement getResetPassword() {
		clickMenuAccount();
		return this.resetPassword;
	}

	public WebElement getLogout() {
		clickMenuAccount();
		return this.logout;
	}

	// Functional
	
	public String getLoginAccountText() {
		return getLoginAccount().getText();
	}

	public String getChangePasswordText() {
		return getChangePassword().getText();
	}

	public String getResetPasswordText() {
		return getResetPassword().getText();
	}

	public String getLogoutText() {
		return getLogout().getText();
	}

	// set Data

	public void clickLoginAccount() {
		getLoginAccount().click();
	}

	public void clickMenuAccount() {
		clickLoginAccount();
		getMenuAccount().click();
		this.changePassword = driver.findElement(By.cssSelector("a.change-password"));
		this.resetPassword = driver.findElement(By.cssSelector("a.reset-my-password"));
		this.logout = driver.findElement(By.xpath("//a[contains(@href,'/logout')]"));
	}

	public void clickChangePassword() {
		getChangePassword().click();
	}

	public void clickResetPassword() {
		getResetPassword().click();
	}

	public void clickLogout() {
		getLogout().click();
	}

}
