Cancel my flight and have the awards returned to the corresponding account.

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to cancel a reservation purchased with two transitional awards
As an Anonymous User
I want to retrieve a reservation to cancel it. be informed that my reservation was purchased using two transitional awards and complete the cancel process

Scenario: Anonymous User cancels a reservation purchased with two transitional awards
Given I am a Rapid Rewards Member passenger with freedom awards
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+4|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|FreedomAward|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|FreedomAward|
    |inboundRouting|Nonstop|

And I have booked this flight with 2 alternate freedom award(s)
When I retrieve my air reservation to cancel it without being logged-in
Then I see the Southwest Airlines - Cancel Air Reservation page
And I see the information of the 2 award(s) to be returned
When I confirm the air cancellation
Then I see the Cancellation Confirmation Page
And I see a message which informs that 2 award(s) will be returned to the corresponding rapid rewards account