package com.test.ws.utils;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ws.exception.BusinessException;
import com.test.ws.logger.Logger;


public class ContextListener implements ServletContextListener{

	private static String MODULE = ContextListener.class.getSimpleName();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		  ClassLoader cl = Thread.currentThread().getContextClassLoader();
          // Loop through all drivers
          Enumeration<Driver> drivers = DriverManager.getDrivers();
          while (drivers.hasMoreElements()) {
              Driver driver = drivers.nextElement();
              if (driver.getClass().getClassLoader() == cl) {
                  // This driver was registered by the webapp's ClassLoader, so deregister it:
                  try {
                      System.out.println("Deregistering JDBC driver {}" + driver);
                      DriverManager.deregisterDriver(driver);
                  } catch (SQLException ex) {
                      System.out.println("Error deregistering JDBC driver {}" + ex);
                  }
              } else {
                  // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                  System.out.println("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader" + driver);
              }
          }
      }

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		System.out.println("Initializing Logger.....");
		String contextPath = sce.getServletContext().getRealPath("");

		ServletContext servletContext = sce.getServletContext();
		String log4jFile = servletContext.getInitParameter("log4jFileName");
		System.out.println("log4j configuration file:'" + log4jFile + "'");
		String fullPath = contextPath + File.separator + log4jFile;
		PropertyConfigurator.configure(fullPath);
		initilizeTokenList();
	}
	private void initilizeTokenList() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createSQLQuery("select auth_token from users");
			List<String> list = query.list();
			for(String str : list) {
				TokenGenerator.tokenMap.put(str,str);
			}
		} catch (BusinessException be) {
			Logger.logError(MODULE, be.getMessage());
		}
	}
}
