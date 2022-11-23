package stepDef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import schemas.Identity;
import utils.PropertiesUtil;
import static utils.APIUtils.*;

public class Task1Steps {
	PropertiesUtil props = new PropertiesUtil();

	private Response response;
	private HashMap<String, Integer> currencies = new HashMap<String, Integer>();

	@Given("I retrieve the IDs for the symbols")
	public void retrieve_ids(DataTable dataTable) {
		List<String> symbols = dataTable.asList();
		response = doGet(props.getMapApi());
		Identity[] identities = response.jsonPath().getObject("data", Identity[].class);
		for (Identity identity : identities) {
			if (symbols.contains(identity.symbol)) {
				// System.out.println("symbol: " + identity.symbol);
				// System.out.println("id: " + identity.id);
				currencies.put(identity.symbol, identity.id);
			}
		}
		// System.out.println(currencies);
	}

	@Then("I convert with {string} currency")
	public void price_conversion(String currency) {
		Map<String, String> queryParams = new HashMap<String, String>();
		currencies.forEach((key, val) -> {
			queryParams.put("id", val + "");
			queryParams.put("convert", currency);
			queryParams.put("amount", "1");
			response = doGet(props.getPriceConvApi(), queryParams);
			System.out.println(
					key + "\t|\t" + val + "\t|\t" + response.jsonPath().getString("data.quote." + currency + ".price"));
		});
	}	
}
