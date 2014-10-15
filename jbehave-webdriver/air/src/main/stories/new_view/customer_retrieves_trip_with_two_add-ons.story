Book an Air product with two add-ons

Meta:
@flow air
@process view
@project tmAir
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken

Narrative:
In order to validate the Business Select and Early-Bird Check-in add-ons
As a User
I want to purchase a flight on Business Select for the outbound and Early-Bird for the inbound and be shown only the EB information after retrieving the reservation

Scenario: The user purchases a flight with two add-ons (Business Select and EarlyBird checkin) and after retrieving the reservation views only the EB icon
Given I am flying a round-trip Southwest flight EB eligible with Nonstop segments
And I have selected BS for the outbound flight and AT for the inbound flight and continued to purchase page
And I have added the EB checkin to the flight
And I have completed the booking process adding the Air product to a new trip named MyTrip
When I retrieve my itinerary
Then I see an icon which informs that the EB Checkin has been added to the flight on the View Reservation Page
And I should not see any references to the Business Select