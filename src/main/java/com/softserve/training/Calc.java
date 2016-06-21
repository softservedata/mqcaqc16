package com.softserve.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calc {
	private static final Logger logger = LoggerFactory.getLogger(Calc.class);

	public void calcMethod() {
		logger.error("Calc Error");
		logger.warn("Calc Warning");
		logger.info("Calc Info");
		logger.debug("Calc Debug");
	}

}
