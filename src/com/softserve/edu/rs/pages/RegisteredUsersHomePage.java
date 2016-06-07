package com.softserve.edu.rs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisteredUsersHomePage extends AdminHomePage {
	
	// Fields
	private WebElement name;

	public RegisteredUsersHomePage(WebDriver driver) {
		super(driver);
		this.name = driver.findElement(By.id("firstName"));
	}
	
    // PageObject

	// get Data

	public WebElement getName() {
		return this.name;
	}

	// Functional
	
	public String getNameText() {
		return getName().getText();
	}
	
	// set Data

	public void clickName() {
		getName().click();
	}

	public void clearName() {
		clickName();
		getName().clear();
	}

	public void setName(String login) {
		clickName();
		getName().sendKeys(login);
	}

	public void setNameClear(String login) {
		clearName();
		getName().sendKeys(login);
	}

    // Business Logic

}
