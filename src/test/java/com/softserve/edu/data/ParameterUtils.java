package com.softserve.edu.data;

import java.util.HashMap;

import org.testng.ITestContext;

public class ParameterUtils {
	private static volatile ParameterUtils instance = null;

	private ParameterUtils() {
	}

	public static ParameterUtils get() {
		if (instance == null) {
			synchronized (ParameterUtils.class) {
				if (instance == null) {
					instance = new ParameterUtils();
				}
			}
		}
		return instance;
	}

	public ApplicationSources updateApplicationSources(ApplicationSources applicationSources, ITestContext context) {
		// From TestNG.xml Context
		if (context != null) {
	        HashMap<String, String> testParameters = new HashMap<String, String>(context.getCurrentXmlTest().getAllParameters());
	        for (String key : testParameters.keySet()) {
	        	System.out.println("Test parameter: key=" + key + " value=" + testParameters.get(key));
	        	if (key.toLowerCase().equals("browsername")) {
	        		applicationSources.setBrowserName(testParameters.get(key));
	        		continue;
	        	} else if (key.toLowerCase().equals("driverpath")) {
	        		applicationSources.setDriverPath(testParameters.get(key));
	        		continue;
	        	} else if (key.toLowerCase().equals("loginurl")) {
	        		applicationSources.setLoginUrl(testParameters.get(key));
	        		continue;
	        	} else if (key.toLowerCase().equals("logouturl")) {
	        		applicationSources.setLogoutUrl(testParameters.get(key));
	        		continue;
	        	}
	        }
		}
		// From pom.xml Properties
		applicationSources = updateBrowserNameByPOM(applicationSources);
		return applicationSources;
	}

	public ApplicationSources updateBrowserNameByPOM(ApplicationSources applicationSources) {
		if (System.getProperty("browser.name") != null) {
			applicationSources.setBrowserName(System.getProperty("browser.name"));
			System.out.println("updateBrowserNameByPOM: browserName = " + System.getProperty("browser.name"));
		}
		return applicationSources;
	}
	
}
