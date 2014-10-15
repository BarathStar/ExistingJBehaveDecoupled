View WN* info in Shopping Cart


Meta:
@project cr1
@airTranOnly
@flow air
@process Information Search
@user anonymous
@traveler adult
@storyId DCAIR4811
@data schedule
@dyna_stubs


Narrative:
In order to see the SouthWest and AirTran Flight I am booking
As a customer
I want to view my shopping cart


Scenario: Viewing a FL Only Itinerary

Given I am flying a round-trip AirTran AirTran flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I see AirTran indicators in the shopping cart
