Retrieve a reservation purchased using Rapid Rewards point in order to cancel it so that I am informed that my reservation was purchased with points.

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to cancel a reservation purchased with points
As an Anonymous User
I want to retrieve a reservation to cancel it and be informed that my reservation was purchased using Rapid Rewards points.

Scenario: Anonymous User wants to cancel a reservation purchased with rapid rewards points
Given I am a Rapid Rewards Member with enough points to complete a purchase
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+4|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have booked this flight using my points
When I retrieve my air reservation to cancel it without being logged-in
Then I see the Cancel Air Reservation Page
And I see a message which informs that the points will be returned to the corresponding rapid rewards account