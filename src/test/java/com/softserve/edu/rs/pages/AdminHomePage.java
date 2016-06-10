package com.softserve.edu.rs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHomePage extends ACommonPage {

	// Fields
	private WebElement home;
	private WebElement users;
	private WebElement settings;
	private WebElement communities;
	private WebElement newUser;
	private WebElement unblockAll;
	//
	private WebElement active;
	private WebElement inactive;
	private WebElement nonConfirmed;
	private WebElement blocked;

	public AdminHomePage(WebDriver driver) {
        super(driver);
    	this.home = driver.findElement(By.cssSelector("a.glyphicon.glyphicon-home"));
    	this.users = driver.findElement(By.xpath("//li[@class='dropdown']"));
    	this.settings = driver.findElement(By.xpath("//a[contains(@href,'/settings')]"));
    	this.communities = driver.findElement(By.xpath("//a[contains(@href,'/communities/show-all-communities')]"));
    	this.newUser = driver.findElement(By.xpath("//a[contains(@href,'/manualregistration')]"));
    	this.unblockAll = driver.findElement(By.id("unlockUsers"));
    }

    // PageObject

	// get Data

	public WebElement getHome() {
		return this.home;
	}

	public WebElement getUsers() {
		return this.users;
	}

	public WebElement getSettings() {
		return this.settings;
	}

	public WebElement getCommunities() {
		return this.communities;
	}

	public WebElement getNewUser() {
		return this.newUser;
	}

	public WebElement getUnblockAll() {
		return this.unblockAll;
	}

	// Functional
	
	public String getHomeText() {
		return getHome().getText();
	}

	public String getUsersText() {
		return getUsers().getText();
	}

	public String getSettingsText() {
		return getSettings().getText();
	}

	public String getCommunitiesText() {
		return getCommunities().getText();
	}

	public String getNewUserText() {
		return getNewUser().getText();
	}

	public String getUnblockAllText() {
		return getUnblockAll().getText();
	}

	// get Data

	public WebElement getActive() {
		clickUsers();
		return this.active;
	}
	
	public WebElement getInactive() {
		clickUsers();
		return this.inactive;
	}
	
	public WebElement getNonConfirmed() {
		clickUsers();
		return this.nonConfirmed;
	}
	
	public WebElement getBlocked() {
		clickUsers();
		return this.blocked;
	}
	
	// set Data
    
	public void clickHome() {
		getHome().click();
	}

	public void clickUsers() {
		clickLoginAccount();
		getUsers().click();
		this.active = driver.findElement(By.xpath("//ul/li[1]/a[contains(@href,'/users/get-all-users')]"));
		this.inactive = driver.findElement(By.xpath("//a[contains(@href,'/users/get-all-users?statusType=inactive')]"));
		this.nonConfirmed = driver.findElement(By.xpath("//a[contains(@href,'/users/get-all-users?statusType=notcomfirmed')]"));
		this.blocked = driver.findElement(By.xpath("//a[contains(@href,'/users/get-all-users?statusType=block')]"));
	}

	public void clickSettings() {
		getSettings().click();
	}

	public void clickCommunities() {
		getCommunities().click();
	}

	public void clickNewUser() {
		getNewUser().click();
	}

	public void clickUnblockAll() {
		getUnblockAll().click();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public void clickActive() {
		getActive().click();
	}
	
	public void clickInactive() {
		getInactive().click();
	}
	
	public void clickNonConfirmed() {
		getNonConfirmed().click();
	}
	
	public void clickBlocked() {
		getBlocked().click();
	}

    // Business Logic

    public AdminHomePage changeLanguage(ChangeLanguageFields language) {
    	setChangeLanguage(language);
        // Return a new page object representing the destination.
        return new AdminHomePage(driver);
    }

    public LoginPage logout() {
    	clickLogout();
        // Return a new page object representing the destination.
        return new LoginPage(driver);
    }
    
    public RegisteredUsersHomePage gotoRegisteredUsers() {
    	clickNewUser();
        return new RegisteredUsersHomePage(driver);
    }

}
