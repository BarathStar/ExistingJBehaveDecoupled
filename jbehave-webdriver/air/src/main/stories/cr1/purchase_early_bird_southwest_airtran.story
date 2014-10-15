View EarlyBird on Purchase Page for southwest segment only

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
I want to purchase EB for a southwest-AirTran flight
So that I can verify Early Bird on the purchase page for only the southwest segment

Scenario: Verify Early Bird not available for AirTran segments on the purchase page

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am a customer searching for round-trip flights from the search flights page
When I select, price and view the Purchase page for a flight
And I fill out the purchase form with Early Bird
Then I should not be able to purchase EB for AirTran segments
And I should be able to purchase EB for Southwest segments
When I continue to Confirmation page from Purchase page
Then I should see the confirmation page
And Early Bird is present on the confirmation page
