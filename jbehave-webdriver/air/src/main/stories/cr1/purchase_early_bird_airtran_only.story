View EarlyBird not available on Purchase Page for FL only itinerary

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCAIR6397 ZR-891
@project_in_dev


Narrative:
As a customer
I want to purchase EB for an AirTran only flight
So that I can verify Early Bird not available for AirTran only flights on the purchase page


Scenario: Verify Early Bird not available for AirTran only Itinerary

Given I am flying a round-trip AirTran AirTran flight
And I am a customer searching for round-trip flights from the search flights page
When I select, price and view the Purchase page for a flight
And I fill out the purchase form
Then I should see Early Bird unavailable message for AirTran
