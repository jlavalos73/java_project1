package com.ers.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

	public static Logger log = LogManager.getLogger(LoggerUtil.class);
	
	private LoggerUtil() {
		super();
	}
	public static Logger getLogger() {
		return log;
	}
}
