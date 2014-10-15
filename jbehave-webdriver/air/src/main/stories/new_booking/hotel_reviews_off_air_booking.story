Hotel reviews toggle off on price page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft

Narrative:
As a southwest.com customer
Given I pick flights on the select flight page
And the hotel reviews toggle is off
When I click to continue
Then I can successfully make a booking

Scenario: adult air purchase with hotel ratings toggle off
Given I search for a roundtrip air ticket from DAL to HOU
When I change the toggle named: HOTEL_REVIEWS to value: false
When I search and book a flight from search flights page
Then I receive an air confirmation number