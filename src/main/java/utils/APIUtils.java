package utils;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class APIUtils {
	static PropertiesUtil props = new PropertiesUtil();
	public static Response doGet(String uri) {
		return given().baseUri(props.getUrl()).header("X-CMC_PRO_API_KEY", props.getApiKey()).when().get(uri);
		// System.out.println("API Response: " + response.asPrettyString());
	}

	public static Response doGet(String uri, Map<String, String> queryParams) {
		return given().baseUri(props.getUrl()).header("X-CMC_PRO_API_KEY", props.getApiKey()).when()
				.queryParams(queryParams).get(uri);
		// System.out.println("API Response: " + response.asPrettyString());
	}
}