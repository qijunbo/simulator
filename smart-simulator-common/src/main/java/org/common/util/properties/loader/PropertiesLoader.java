package org.common.util.properties.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static final String CONFIG_PROPERTIES = "./config.properties";

	protected String cfgFilePath;

	private Properties properties = new Properties();

	public PropertiesLoader() {
		this(CONFIG_PROPERTIES);
	}

	public PropertiesLoader(String file) {
		this.cfgFilePath = file;
		File cfgFile = new File(cfgFilePath);
		InputStream inputStream = null;
		try {
			if (cfgFile.exists()) {
				inputStream = new FileInputStream(cfgFile);
			} else {
				this.getClass().getClassLoader()
						.getResourceAsStream(cfgFilePath);
			}

			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String get(String key) {
		return (String) properties.get(key);
	}

}
