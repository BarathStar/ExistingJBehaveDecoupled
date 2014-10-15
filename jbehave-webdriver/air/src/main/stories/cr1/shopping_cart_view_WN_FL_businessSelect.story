View WN\WN* info in Shopping Cart


Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCQA50 ZR-895
@not_passing draft

Narrative:
As a customer
I want to view my shopping cart
So that I can see the SouthWest and AirTran Flight info in Shopping cart

Scenario: Viewing a WN\FL Only Itinerary

Given I am flying a BusinessSelect round-trip SouthwestCodeshare AirTran flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I see FL Return flight in the shopping cart
And I see Business Select in the shopping cart
When I select to remove the shopping cart
Then I see the confirm remove modal
