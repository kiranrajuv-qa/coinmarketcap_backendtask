package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private Properties properties;
	private final String propertyFilePath = "src//main//resources//";
	private String url, apiKey, mapApi, priceConvApi;

	public PropertiesUtil() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath + "env.properties"));
			properties = new Properties();
			try {
				properties.load(reader);
				url = properties.getProperty("url");
				apiKey = properties.getProperty("apiKey");
				mapApi = properties.getProperty("mapApi");
				priceConvApi = properties.getProperty("priceConvApi");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ignore) {
			}
		}
	}

	public String getUrl() {
		if (url != null)
			return url;
		else
			throw new RuntimeException("URL not specified in env.properties file for the Key: url");
	}

	public String getApiKey() {
		if (apiKey != null)
			return apiKey;
		else
			throw new RuntimeException("API Key not specified in env.properties file for the Key: apiKey");
	}
	
	public String getMapApi() {
		if (mapApi != null)
			return mapApi;
		else
			throw new RuntimeException("mapApi not specified in env.properties file");
	}
	
	public String getPriceConvApi() {
		if (priceConvApi != null)
			return priceConvApi;
		else
			throw new RuntimeException("priceConvApi not specified in env.properties file");
	}
}
