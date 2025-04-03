Feature: Verify Shipping Rate Calculator

  Scenario: User calculates shipping rate on Pos Malaysia website
    Given User navigates to "https://pos.com.my/send/ratecalculator"

    When User enters "35600" in the "From" postcode field
    When User selects "India" as the "To" country
    When User leaves the "To" postcode field empty
    When User enters "1" in the "Weight" field
    When User clicks on Calculate
    Then User should see multiple shipping quotes and options