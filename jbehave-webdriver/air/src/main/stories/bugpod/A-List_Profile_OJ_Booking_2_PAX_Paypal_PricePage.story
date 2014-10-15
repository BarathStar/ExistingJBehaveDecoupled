A-list prefered open jaw booking for two passengers using paypal

Meta:
@suite bugpod

Scenario: Verify Price Page
Given I am logged in as an A-list member
And I navigate to the book a flight page
And I search for an open-jaw flights from DAL to HOU returning to ATL
When I select flights and continue
Then I should see the hotel upsell widget
And I should see the car upsell widget
And I see the shopping cart is expanded