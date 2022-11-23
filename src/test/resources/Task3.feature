Feature: Info API - Filters

	Scenario: Get currencies by tags
		Given I retrieve the data for the id range
			|	10	|
		Then I filter the currencies which has tag
			|	mineable	|