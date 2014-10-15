Purchase Flights with AirTran Segment

Meta:
@project cr1
@airTranOnly
@flow air
@traveler adult
@not_passing draft
@message This story does not belong to any suite. It was drafted in order to not break the storymanager generator.

Scenario: Purchase an Adult AirTran Flight and record some performance metrics
Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I only search a flight
When I wait for yslow performance data json to be logged in '1-flight.select-flight.html.json'
When I select flights and continue
When I wait for yslow performance data json to be logged in '2-reservations.price-reservations.html.json'


When I click continue to the Purchase page
When I wait for yslow performance data json to be logged in '3-reservations.book-reservations.html.json'
