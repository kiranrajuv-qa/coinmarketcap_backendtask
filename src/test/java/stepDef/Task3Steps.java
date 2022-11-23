package stepDef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import utils.PropertiesUtil;
import static utils.APIUtils.*;

public class Task3Steps {
	PropertiesUtil props = new PropertiesUtil();

	private Response response;
	private String id = "";

	@Given("I retrieve the data for the id range")
	public void retrieve_data_for_id_range(DataTable dataTable) {
		int id_range = Integer.parseInt(dataTable.cell(0, 0));
		for(int i=1;i<=id_range;i++) {
			id += i + "";
			if(i != id_range)
				id += ",";
		}
		//System.out.println(id);
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("id", id);
		response = doGet(props.getInfoApi(), queryParams);
		//System.out.println(response.asPrettyString());
	}

	@Then("I filter the currencies which has tag")
	public void verify_tags(DataTable dataTable) {
		String tag = dataTable.cell(0, 0);
		String[] idList = id.split(",");
		for(int i=0;i<idList.length;i++) {
			List<String> tagList = response.getBody().jsonPath().getList("data." + idList[i] + ".tags", String.class);
			//System.out.println(tagList);
			for(String tagItem : tagList)
				if(tagItem.equals(tag))
					System.out.println("Currency with id " + idList[i] + " contains tag: " + tag);
		}
	}
}