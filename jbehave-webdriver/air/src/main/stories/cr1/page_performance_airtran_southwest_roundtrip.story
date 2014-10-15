Purchase Flights with AirTran Segment

Meta:
@flow air
@traveler adult
@project cr1
@airTranOnly

Scenario: Purchase an Adult AirTran Flight and record some performance metrics
Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am on the Homepage
When I only search a flight
And I select flights and continue
And I click continue to the Purchase page
