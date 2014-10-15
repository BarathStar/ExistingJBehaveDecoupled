A-list prefered open jaw booking for two passengers using paypal

Meta:
@suite bugpod

Scenario: Verify Confirmation page
Given I am logged in as an A-list member
And I navigate to the book a flight page
And I search for an open-jaw flights from DAL to HOU returning to ATL
And I select flights and continue
And I click continue to the Purchase page
And I select text me for the contact method
And I select paypal as the payment method
And I select to name my trip
And I purchase the flight
When I enter PayPal credentials and pay for my flight
Then I receive an air confirmation number
And I verify my stations are displayed on the confirmation page along with the car and hotel cross sell
And I am able to change and cancel my itinerary