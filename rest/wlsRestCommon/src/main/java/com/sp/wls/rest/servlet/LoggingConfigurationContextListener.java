package com.sp.wls.rest.servlet;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoggingConfigurationContextListener implements ServletContextListener {

	private static final String WLS_LOGBACK_CONFIGURATION_FILE = "wls.logback.configurationFile";
	private static final String WLS_LOGBACK_CONTEXT_NAME = "wls.logback.context.name";

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		String filePath = System.getProperty(WLS_LOGBACK_CONFIGURATION_FILE);
		if(StringUtils.isEmpty(filePath)){
			return;
		}

		Path configFilePath = Paths.get(filePath);
		if (!Files.exists(configFilePath)) {
			return;
		}

		LoggerContext context = LoggerContext.class.cast(LoggerFactory.getILoggerFactory());

		System.setProperty(WLS_LOGBACK_CONTEXT_NAME, context.getName());

		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(context);
		context.reset();
		try {
			configurator.doConfigure(configFilePath.toUri().toURL());
		} catch (JoranException | MalformedURLException e) {
			StatusPrinter.print(context);
		}

		getLogger().trace(String.format("Configured logging for context %s using file %s", context.getName(), configFilePath));
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	private static Logger getLogger() {
		return LoggerFactory.getLogger(LoggingConfigurationContextListener.class);
	}
}
