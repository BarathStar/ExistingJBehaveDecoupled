View WN info in Shopping Cart


Meta:
@project cr1
@airTranOnly
@flow air
@process Information Search
@user anonymous
@traveler adult
@storyId DCAIR4811
@dyna_stubs

Narrative:
In order to see the SouthWest and AirTran Flight I am booking
As a customer
I want to view my shopping cart


Scenario: Viewing a WN Only Itinerary

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I see no AirTran Indicator in the shopping cart
