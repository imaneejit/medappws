package org.gs.medapp.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.gs.medapp.constants.Constants;

public class PropertiesHelper 
{
	// logger
	private final Logger _log = Logger.getLogger( PropertiesHelper.class );
	
	private static PropertiesHelper helper = new PropertiesHelper();
	private final Properties properties = new Properties();
	
	private PropertiesHelper()
	{
		init();
	}
	
	public static PropertiesHelper getInstance()
	{
		return helper;
	}
	
	public Properties getProperties()
	{
		return properties;
	}

	private void init()
	{
		try 
		{
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(Constants._STR_MEDAPPWS_PROPS_FILENAME);
			properties.load(inputStream);
		} 
		catch (FileNotFoundException fnfe) 
		{
			_log.error("-----> Unable to load properties file.", fnfe);
		} 
		catch (IOException ioe) 
		{
			_log.error("-----> Unable to load properties file.", ioe);
		}
		finally 
		{
			
		}
	}
}
