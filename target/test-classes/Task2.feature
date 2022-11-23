Feature: Info API

  Scenario: Verify Info API
    Given I retrieve the data for the id "1027"
    Then I confirm that the following logo URL is present
      | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png |
    And I confirm that the following technical_doc URL is present
      | https://github.com/ethereum/wiki/wiki/White-Paper |
    And I confirm the symbol of currency
      | ETH |
    And I confirm the date added
      | 2015-08-07T00:00:00.000Z |
    And I confirm that the currency has below tag associated with it
      | usv-portfolio |
