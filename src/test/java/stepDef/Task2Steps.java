package stepDef;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import utils.PropertiesUtil;
import static utils.APIUtils.*;

public class Task2Steps {
	PropertiesUtil props = new PropertiesUtil();

	private Response response;
	private String id = null;
	
	@Given("I retrieve the data for the id {string}")
	public void retrieve_data_for_id(String id) {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("id", id);
		this.id = id;
		response = doGet(props.getInfoApi(), queryParams);
		//System.out.println(response.asPrettyString());
	}

	@Then("I confirm that the following logo URL is present")
	public void verify_logo(DataTable dataTable) {
		String logo_url = dataTable.cell(0, 0);
		Assert.assertEquals(response.jsonPath().getString("data." + id + ".logo"), logo_url);
	}

	@Then("I confirm that the following technical_doc URL is present")
	public void verify_tech_doc_url(DataTable dataTable) {
		String tech_doc_url = dataTable.cell(0, 0);
		response
			.then()
			.assertThat()
			.body("data." + id + ".urls.technical_doc", hasItem(tech_doc_url));
	}

	@Then("I confirm the symbol of currency")
	public void verify_symbol(DataTable dataTable) {
		String symbol = dataTable.cell(0, 0);
		Assert.assertEquals(response.jsonPath().getString("data." + id + ".symbol"), symbol);
	}

	@Then("I confirm the date added")
	public void verify_date_added(DataTable dataTable) {
		String date_added = dataTable.cell(0, 0);
		Assert.assertEquals(response.jsonPath().getString("data." + id + ".date_added"), date_added);
	}
	
	@Then("I confirm that the currency has below tag associated with it")
	public void verify_tags(DataTable dataTable) {
		String tag = dataTable.cell(0, 0);
		response
			.then()
			.assertThat()
			.body("data." + id + ".tags", hasItem(tag));
	}
}
