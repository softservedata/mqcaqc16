package com.softserve.edu.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

	@Test
	public void testLogin() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://registrator.herokuapp.com/login");
		//
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("admin");
		//
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin");
		//
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		WebElement login = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
		//
		Assert.assertEquals(login.getText(),"admin");
		login.click();
		driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm.dropdown-toggle")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/logout')]")).click();
		//
		Assert.assertNotNull(driver.findElement(By.id("login")));
		Assert.assertEquals(driver.findElement(By.id("login")).getAttribute("name"),"login");
		Thread.sleep(2000);
		driver.quit();
	}
}
