A-list prefered open jaw booking for two passengers using paypal

Meta:
@suite bugpod

Scenario: Verify Purchase Page
Given I am logged in as an A-list member
And I navigate to the book a flight page
And I search for an open-jaw flights from DAL to HOU returning to ATL
And I select flights and continue
When I click continue to the Purchase page
Then I see the shopping cart partially collapsed