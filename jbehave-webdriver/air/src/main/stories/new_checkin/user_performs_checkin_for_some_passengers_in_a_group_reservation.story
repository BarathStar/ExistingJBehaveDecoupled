Check-in round-trip anytime air ticket for some adults

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project group_checkin
@dyna_stubs
@not_live

Narrative:
In order to get our boarding passes
As an adult traveling with 9 other adults
I want to check-in online for selected adults

Scenario: Performs check-in for selected passengers in a group reservation
Given I am:
    |Field|Value|
    |adultCheckInPassengerCount|10|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have booked this group reservation with all its passengers eligible for checkin online
When I retrieve my reservation to checkin
And I select some passengers available for checkin
And I confirm to start the checkin process
Then I see all the selected passengers under the Checkin Results section
And I see the non-selected passengers available for checkin online
