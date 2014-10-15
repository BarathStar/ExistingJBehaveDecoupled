View WN*\WN info in Shopping Cart


Meta:
@project cr1
@airTranOnly
@flow air
@process Information Search
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to see the SouthWest and AirTran Flight I am booking
As a customer
I want to view my shopping cart


Scenario: Viewing a FL WN Round Trip Itinerary

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
And I collapse the shopping cart
Then I see no AirTran Indicator
