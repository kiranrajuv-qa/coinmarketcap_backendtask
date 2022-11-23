Feature: Price Conversion

  Scenario: Retrieve IDs and do Price Conversion
    Given I retrieve the IDs for the symbols
      | BTC  |
      | USDT |
      | ETH  |
    Then I convert with "BOB" currency
