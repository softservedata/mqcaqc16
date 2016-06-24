package com.softserve.edu.rs.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.softserve.edu.data.ApplicationSources;

public class Application {
	
	public static enum ReporterLevels {
        SCREENSHOT_LEVEL(2),
        ERROR_LEVEL(2), // 3
        WARNING_LEVEL(5),
        INFO_LEVEL(7),
        DEBUG_LEVEL(9);
        private int level;

        private ReporterLevels(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }
    }
	
	private WebDriver driver;
	private ApplicationSources applicationSources;
	//
	private final String TIME_TEMPLATE = "yyyy_MM_dd HH-mm-ss";
    private final String FILE_SUFFIX = " CaptureScreenImage.png";
    private final String DEFAULT_DIRECTORY = "./test-output";
    private final String MAVEN_DIRECTORY = "surefire.reports.directory";
    private final String SLASH = "/";
    private final String FAILED_TO_CREATE = "Failed to create screenshot: %s";
	
	private Application(ApplicationSources applicationSources) {
		this.applicationSources = applicationSources;
	}
	
	public static Application get(ApplicationSources applicationSources) {
		Application instance = new Application(applicationSources);
		instance.driver = instance.getWebDriver();
		instance.driver.manage().timeouts()
			.implicitlyWait(applicationSources.getImplicitTimeOut(), TimeUnit.SECONDS);
		// TODO Setting
		return instance;
	}
	
	public LoginPage load() {
		driver.get(applicationSources.getLoginUrl());
		return new LoginPage(driver);
	}
	
	public LoginPage logout() {
		driver.get(applicationSources.getLogoutUrl());
		return new LoginPage(driver);
	}
	
	public void quit() {
		if (driver != null) {
			driver.quit();
		}
	}

	private WebDriver getWebDriver() {
		if (applicationSources.getBrowserName().toLowerCase().equals("firefox")) {
			return getFirefoxDriver();
		} else if (applicationSources.getBrowserName().toLowerCase().equals("chrome")) {
			return getChromeDriver();
		} else if (applicationSources.getBrowserName().toLowerCase().contains("htmlunit")) {
			return getHtmlUnitDriver();
		} 
		// Get Default Browser
		return getFirefoxDriver();
	}

	private WebDriver getFirefoxDriver() {
		driver = new FirefoxDriver();
		return driver;
	}
	
	private WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				applicationSources.getDriverPath());
		driver = new ChromeDriver();
		return driver;
	}

	private WebDriver getHtmlUnitDriver() {
		driver = new HtmlUnitDriver(true);
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		return driver;
	}

	private String getCurrentTime() {
		return new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
	}

	private String getOutputDirectory() {
        String outputDirectory = System.getProperty(MAVEN_DIRECTORY);
        if ((outputDirectory == null) || (outputDirectory.isEmpty())) {
            outputDirectory = DEFAULT_DIRECTORY;
        }
        return outputDirectory + SLASH;
    }

	private String getAbsolutePathFileName() {
		return getOutputDirectory() + getCurrentTime() + FILE_SUFFIX;
	}
	
	/**
	 * @return Absolute path of filename.
	 */
	public String captureScreen() {
		String absolutePathFileName = null;
		if (!applicationSources.getBrowserName().toLowerCase().contains("htmlunit")) {
			absolutePathFileName = getAbsolutePathFileName();
			try {
	            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(srcFile, new File(absolutePathFileName));
			} catch (Exception e) {
				throw new RuntimeException(String.format(FAILED_TO_CREATE,
						absolutePathFileName), e);
			}
		}
		return absolutePathFileName;
	}

}
